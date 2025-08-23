// src/com/semi/service/user/dao/UserDao.java
package com.semi.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.time.LocalDate;

import com.semi.common.util.DBUtil;   // 팀 프로젝트에서 쓰는 커넥션 유틸 가정
import com.semi.domain.User;         // ✅ 도메인 기준으로 맞춤 (com.semi.domain.User)

/**
 * UserDao
 * - DB 테이블(users) <-> 도메인(User) 매핑 담당
 * - 도메인은 LocalDate 사용 → 여기서 java.sql.Date로 변환하여 JDBC에 바인딩
 * - 비밀번호는 해시 문자열(HEX)로 저장/조회
 * - status : 'Y' 활성, 'N' 비활성(탈퇴)
 */
public class UserDao {

    // 컬럼 리스트 상수(실수 방지 & 일관성)
    private static final String COLS =
        "userid, password, region, name, birthdate, phone, gender, category, status";

    /** ============ C : INSERT(회원가입) ============ */
    public void insert(User u) throws Exception {
        // 필수값이 null이면 JDBC에서 NPE가 날 수 있으므로, 여기서 선제 체크 권장
        if (u == null) throw new IllegalArgumentException("user is null");
        if (isEmpty(u.getUserId())) throw new IllegalArgumentException("userId is required");
        if (isEmpty(u.getPassword())) throw new IllegalArgumentException("password is required(여기선 해시문자열이어야 함)");
        if (isEmpty(u.getRegion())) throw new IllegalArgumentException("region is required");
        if (isEmpty(u.getName())) throw new IllegalArgumentException("name is required");
        if (u.getBirthDate() == null) throw new IllegalArgumentException("birthDate is required");
        if (isEmpty(u.getPhone())) throw new IllegalArgumentException("phone is required");
        if (isEmpty(u.getGender())) throw new IllegalArgumentException("gender is required");
        if (isEmpty(u.getStatus())) throw new IllegalArgumentException("status is required (Y/N)");

        System.out.println("[USER][DAO] insert userId=" + u.getUserId());

        final String sql = "INSERT INTO users (" + COLS + ") VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 1) PK/계정
            ps.setString(1, u.getUserId());            // userid (PK)
            ps.setString(2, u.getPassword());          // password (이미 SHA-256 해시된 문자열)

            // 2) 프로필/연락
            ps.setString(3, u.getRegion());            // region
            ps.setString(4, u.getName());              // name
            ps.setDate(5, toSqlDate(u.getBirthDate())); // birthdate (LocalDate → java.sql.Date)
            ps.setString(6, u.getPhone());             // phone
            ps.setString(7, u.getGender());            // gender

            // 3) 분류/상태
            ps.setString(8, nullToEmpty(u.getCategory())); // category (NULL 허용 → null이면 빈문자 저장 또는 setNull로 바꿔도 됨)
            ps.setString(9, u.getStatus());             // status ('Y'/'N')

            ps.executeUpdate();
        }
    }

    /** ============ R : SELECT 단건(아이디 기준) ============ */
    public User findById(String userId) throws Exception {
        if (isEmpty(userId)) return null;

        final String sql =
            "SELECT " + COLS + " FROM users WHERE userid = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }

        return null; // 없을 경우 null
    }

    /** ============ R : 존재 여부(중복체크 등) ============ */
    public boolean exists(String userId) throws Exception {
        if (isEmpty(userId)) return false;

        final String sql = "SELECT 1 FROM users WHERE userid = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // 레코드 있으면 true
            }
        }
    }

    /** ============ U : 비번 제외 기본 정보 수정 ============ */
    public void update(User u) throws Exception {
        if (u == null || isEmpty(u.getUserId()))
            throw new IllegalArgumentException("userId is required");

        System.out.println("[USER][DAO] update userId=" + u.getUserId());

        final String sql =
            "UPDATE users " +
            "   SET region=?, name=?, birthdate=?, phone=?, gender=?, category=? " +
            " WHERE userid=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getRegion());                 // region
            ps.setString(2, u.getName());                   // name
            ps.setDate(3, toSqlDate(u.getBirthDate()));     // birthdate (LocalDate → java.sql.Date)
            ps.setString(4, u.getPhone());                  // phone
            ps.setString(5, u.getGender());                 // gender
            ps.setString(6, nullToEmpty(u.getCategory()));  // category
            ps.setString(7, u.getUserId());                 // WHERE userid=?

            ps.executeUpdate();
        }
    }

    /** ============ U : 비밀번호 변경(해시 문자열로 교체) ============ */
    public void updatePassword(String userId, String hashedPassword) throws Exception {
        if (isEmpty(userId)) throw new IllegalArgumentException("userId is required");
        if (isEmpty(hashedPassword)) throw new IllegalArgumentException("hashedPassword is required");

        System.out.println("[USER][DAO] updatePassword userId=" + userId);

        final String sql = "UPDATE users SET password=? WHERE userid=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hashedPassword); // 이미 Service에서 SHA-256 등으로 해시된 상태
            ps.setString(2, userId);

            ps.executeUpdate();
        }
    }

    /** ============ U : 상태 변경(Y/N) → 탈퇴/복구 등 ============ */
    public void setStatus(String userId, String status) throws Exception {
        if (isEmpty(userId)) throw new IllegalArgumentException("userId is required");
        if (isEmpty(status)) throw new IllegalArgumentException("status is required");

        System.out.println("[USER][DAO] setStatus userId=" + userId + ", status=" + status);

        final String sql = "UPDATE users SET status=? WHERE userid=?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status); // 'Y' or 'N'
            ps.setString(2, userId);

            ps.executeUpdate();
        }
    }

    // ===================== 내부 유틸/매핑 =====================

    /**
     * ResultSet → User 매핑
     * - DB의 DATE(시간 포함 가능) → java.sql.Date 로 JDBC가 읽어옴
     * - LocalDate만 쓰므로 toLocalDate() 로 변환하여 도메인에 세팅
     * - 비번은 로그인 검증 등에서 필요하므로 VO에 담아 리턴(세션에는 넣지 않는 걸 권장)
     */
    private User map(ResultSet rs) throws Exception {
        User u = new User();

        // 1) PK/계정
        u.setUserId(rs.getString("userid"));
        u.setPassword(rs.getString("password")); // 해시 문자열

        // 2) 프로필/연락
        u.setRegion(rs.getString("region"));
        u.setName(rs.getString("name"));

        // Oracle DATE -> java.sql.Date -> LocalDate
        java.sql.Date birthSqlDate = rs.getDate("birthdate");
        LocalDate birthLocal = toLocalDate(birthSqlDate);
        u.setBirthDate(birthLocal);

        u.setPhone(rs.getString("phone"));
        u.setGender(rs.getString("gender"));

        // 3) 분류/상태
        u.setCategory(rs.getString("category"));
        u.setStatus(rs.getString("status"));

        return u;
    }

    /** LocalDate → java.sql.Date (JDBC 바인딩용) */
    private static java.sql.Date toSqlDate(LocalDate ld) {
        return (ld == null) ? null : java.sql.Date.valueOf(ld);
    }

    /** java.sql.Date → LocalDate (도메인 세팅용) */
    private static LocalDate toLocalDate(java.sql.Date d) {
        return (d == null) ? null : d.toLocalDate();
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
