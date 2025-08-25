// src/main/java/com/semi/service/volRequest/dao/VolRequestDao.java
package com.semi.service.volRequest.dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import com.semi.common.Page;
import com.semi.common.Search;
import com.semi.common.util.DBUtil;
import com.semi.domain.VolRequest;
import com.semi.service.volRequest.dto.VolRequestListItem;

public class VolRequestDao {

    // ========= 목록 (Map 조립) : Search + category만 받음 =========
    public Map<String, Object> getVolRequestList(Search search, String category, int pageUnit, String status) throws Exception {

        // 페이지 파라미터 안전화
        final int currentPage = Math.max(1, search != null ? search.getCurrentPage() : 1);
        final int pageSize    = Math.max(1, search != null ? search.getPageSize()    : 10);
        final int beginRow    = (currentPage - 1) * pageSize + 1;
        final int endRow      = currentPage * pageSize;

//        final String status   = "모집중"; // 기본 필터

        final int totalCount = countVolRequests(search, category, status);
        final Page page = new Page(currentPage, totalCount, pageUnit, pageSize);

        final List<VolRequestListItem> list =
                (totalCount > 0)
                ? fetchVolRequestList(search, category, status, beginRow, endRow)
                : Collections.emptyList();

        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("page", page);
        map.put("list", list);
        map.put("search", search);
        return map;
    }

    // ========= 총건수 (Search 해석) =========
    private int countVolRequests(Search search, String category, String status) throws Exception {
        StringBuilder where = new StringBuilder(" WHERE v.flag = 'r' ");
        List<Object> params = new ArrayList<>();

        if (notEmpty(status))   { where.append(" AND v.status = ? ");   params.add(status.trim()); }
        if (notEmpty(category)) { where.append(" AND v.category = ? "); params.add(category.trim()); }
        if (notEmpty(status)) {
            where.append(" AND v.status = ? ");
            params.add(status.trim());     // "모집중"
        }

        applySearchCondition(where, params, search);

        final String sql =
            "SELECT COUNT(*) " +
            "  FROM volunteer v " +
            "  JOIN users u ON u.userid = v.authorid " +
                 where.toString();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            bindParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    // ========= 목록 조회 (Search 해석 + 페이징) =========
    private List<VolRequestListItem> fetchVolRequestList(Search search, String category, String status,
                                                         int beginRow, int endRow) throws Exception {
        StringBuilder where = new StringBuilder(" WHERE v.flag = 'r' ");
        List<Object> params = new ArrayList<>();
       
        if (notEmpty(status))   { where.append(" AND v.status = ? ");   params.add(status.trim()); }
        if (notEmpty(category)) { where.append(" AND v.category = ? "); params.add(category.trim()); }

        applySearchCondition(where, params, search);

        final StringBuilder inner = new StringBuilder();
        inner.append("SELECT v.volunteerid, v.title, u.name AS author_name, ")
             .append("       v.category, v.starttime, v.status, ")
             .append("       ROW_NUMBER() OVER (ORDER BY v.createdat DESC) AS rn ")
             .append("  FROM volunteer v ")
             .append("  JOIN users u ON u.userid = v.authorid ")
             .append(       where.toString());

        final String sql =
            "SELECT volunteerid, title, author_name, category, starttime, status " +
            "  FROM (" + inner + ") " +
            " WHERE rn BETWEEN ? AND ?";

        List<VolRequestListItem> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            int idx = bindParams(ps, params);
            ps.setInt(idx++, beginRow);
            ps.setInt(idx,   endRow);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {              
                    VolRequestListItem item = new VolRequestListItem();
                    item.setVolunteerId(rs.getLong("volunteerid"));
                    item.setTitle(rs.getString("title"));
                    item.setAuthorName(rs.getString("author_name"));
                    item.setCategory(rs.getString("category"));

                    Timestamp st = rs.getTimestamp("starttime");
                    if (st != null) {
                        LocalDateTime ldt = st.toLocalDateTime();
                        item.setDate(ldt.toLocalDate());
                        item.setStartTime(ldt.toLocalTime());
                    }
                    item.setStatus(rs.getString("status"));
                    list.add(item);
                }
            }
        }
        return list;
    }

    // ========= 검색 WHERE 동적 조립 =========
    private void applySearchCondition(StringBuilder where, List<Object> params, Search search) {
        if (search == null) return;
        String cond = safe(search.getSearchCondition());
        String keyword = safe(search.getSearchKeyword());
        if (keyword.isEmpty() || cond.isEmpty()) return;

        switch (cond.toLowerCase()) {
            case "title":
                where.append(" AND v.title LIKE ? ");
                params.add("%" + keyword + "%");
                break;
            case "region":
                where.append(" AND v.region LIKE ? ");
                params.add("%" + keyword + "%");
                break;
            case "author":     // 작성자 '이름' LIKE
                where.append(" AND u.name LIKE ? ");
                params.add("%" + keyword + "%");
                break;
            case "authorid":   // 작성자 '아이디' 정확일치
                where.append(" AND v.authorid = ? ");
                params.add(keyword);
                break;
            case "date":
                String[] parts = keyword.split("~");
                if (parts.length == 2 && notEmpty(parts[0]) && notEmpty(parts[1])) {
                    where.append(" AND TRUNC(v.starttime) BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') ");
                    params.add(parts[0].trim()); params.add(parts[1].trim());
                } else {
                    where.append(" AND TRUNC(v.starttime) = TO_DATE(?, 'YYYY-MM-DD') ");
                    params.add(keyword.trim());
                }
                break;
            default: /* no-op */
        }
    }

    // ========= 공통 유틸 =========
    private int bindParams(PreparedStatement ps, List<Object> params) throws SQLException {
        int idx = 1;
        for (Object p : params) ps.setObject(idx++, p);
        return idx;
    }
    private static boolean notEmpty(String s){ return s!=null && !s.trim().isEmpty(); }
    private static String safe(String s){ return (s==null)? "" : s.trim(); }

    // ========= 이하 상세/등록/수정/처리/삭제는 기존 그대로 =========

    public VolRequest getDetailVolRequest(long volunteerId) throws Exception {
        final String sql =
            "SELECT v.volunteerid, v.title, v.starttime, v.endtime, v.content, v.phone, v.region, " +
            "       v.createdat, v.authorid, v.category, v.status " +
            "  FROM volunteer v WHERE v.flag = 'r' AND v.volunteerid = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, volunteerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                VolRequest vo = new VolRequest();
                vo.setPostId(rs.getLong("volunteerid"));
                vo.setTitle(rs.getString("title"));
                vo.setContent(rs.getString("content"));
                vo.setPhone(rs.getString("phone"));
                vo.setRegion(rs.getString("region"));
                vo.setAuthorId(rs.getString("authorid"));
                vo.setCategory(rs.getString("category"));
                vo.setStatus(rs.getString("status"));
                Timestamp st = rs.getTimestamp("starttime");
                Timestamp et = rs.getTimestamp("endtime");
                if (st != null) vo.setStartTime(st.toLocalDateTime());
                if (et != null) vo.setEndTime(et.toLocalDateTime());
                Timestamp ct = rs.getTimestamp("createdat");
                if (ct != null) vo.setCreatedAt(ct.toLocalDateTime());
                vo.setRequestFlag("r");
                return vo;
            }
        }
    }

    public String getAuthorNameByVolunteerId(long volunteerId) throws Exception {
        final String sql =
            "SELECT u.name FROM volunteer v JOIN users u ON u.userid = v.authorid " +
            " WHERE v.flag='r' AND v.volunteerid = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, volunteerId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString(1) : null;
            }
        }
    }

    public long addVolRequest(VolRequest req) throws Exception {
        final String insert =
            "INSERT INTO volunteer (volunteerid, authorid, title, content, phone, region, category, " +
            "                       createdat, starttime, endtime, status, flag) " +
            "VALUES (seq_volunteer.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, '모집중', 'r')";
        final String fetchId = "SELECT seq_volunteer.CURRVAL FROM dual";

        try (Connection con = DBUtil.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(insert)) {
                int idx = 1;
                ps.setString(idx++, req.getAuthorId());
                ps.setString(idx++, req.getTitle());
                ps.setString(idx++, req.getContent());
                ps.setString(idx++, req.getPhone());
                ps.setString(idx++, req.getRegion());
                if (notEmpty(req.getCategory())) ps.setString(idx++, req.getCategory());
                else ps.setNull(idx++, Types.VARCHAR);
                ps.setTimestamp(idx++, Timestamp.valueOf(req.getStartTime()));
                ps.setTimestamp(idx,   Timestamp.valueOf(req.getEndTime()));
                ps.executeUpdate();
            }
            try (PreparedStatement ps2 = con.prepareStatement(fetchId);
                 ResultSet rs = ps2.executeQuery()) {
                return rs.next() ? rs.getLong(1) : 0L;
            }
        }
    }

    public int updateVolRequest(VolRequest req) throws Exception {
        final String sql =
            "UPDATE volunteer SET starttime=?, endtime=?, content=?, phone=?, region=?, title=?, category=? " +
            " WHERE volunteerid=? AND flag='r'";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            int idx = 1;
            ps.setTimestamp(idx++, Timestamp.valueOf(req.getStartTime()));
            ps.setTimestamp(idx++, Timestamp.valueOf(req.getEndTime()));
            ps.setString(idx++, req.getContent());
            ps.setString(idx++, req.getPhone());
            ps.setString(idx++, req.getRegion());
            ps.setString(idx++, req.getTitle());
            if (notEmpty(req.getCategory())) ps.setString(idx++, req.getCategory());
            else ps.setNull(idx++, Types.VARCHAR);
            ps.setLong(idx, req.getPostId());
            return ps.executeUpdate();
        }
    }

    public int updateProcessVolRequest(long volunteerId) throws Exception {
        final String sql =
            "UPDATE volunteer SET status = '모집완료' WHERE volunteerid=? AND flag='r' AND status='모집중'";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, volunteerId);
            return ps.executeUpdate();
        }
    }

    public int deleteVolRequest(long volunteerId) throws Exception {
        final String sql = "DELETE FROM volunteer WHERE volunteerid=? AND flag='r'";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, volunteerId);
            return ps.executeUpdate();
        }
    }
}
