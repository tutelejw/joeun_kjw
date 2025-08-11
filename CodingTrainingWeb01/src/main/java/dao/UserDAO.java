package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DBUtil;

/**
 * UserDAO - users, login_history 관련 DB 처리 클래스
 * 실제 DB에 연결해서 회원가입, 로그인, 회원정보 수정, 로그인 이력 저장 등
 * 사용자와 관련된 작업들을 수행합니다.
 */
public class UserDAO {

    /**
     * 1) 회원가입 - users 테이블에 새 회원 정보를 저장합니다.
     * @param user 저장할 회원 정보가 담긴 User 객체
     * @return 성공 시 1, 실패 시 0
     */
	public int registerUser(User user) throws Exception{
		String sql = "insert into users (id, password, name, role) "
				+ "values (?,?,?,?)";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement psmt= conn.prepareStatement(sql)){
			psmt.setString(1,  user.getId());
			psmt.setString(2,  user.getName());
			psmt.setString(3, user.getPassword());
			psmt.setString(4, user.getRole() == null ? "USER" :user.getRole());
			return psmt.executeUpdate();
		}
	}//  method registerUser end
	
    /**
     * 2) 아이디 중복 체크 - 해당 아이디가 이미 users 테이블에 있는지 확인
     * @param id 체크할 아이디 문자열
     * @return 존재하면 true, 없으면 false
     */
	public boolean isIdExists(String id) throws Exception{
		String sql = "select count(1) cnt from users where id = ?";
		try (Connection conn=DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,id);
				try (ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						return rs.getInt("cnt") > 0;// count가 1 이상이면 존재함 return 메서드종료.
					}
					return false; // 결과가 없으면 존재하지 않음
				}
		}
	}//end of method isIdExists
    /**
     * 3) 로그인 - method  User 입력한 아이디와 비밀번호가 맞으면 User 객체 반환
     * User 클래스는 다음과 같은 필드를 갖고 있습니다.
		String id
		String password
		String name
		String role
     * 다음 요구사항에 맞게 login 메서드를 작성하세요.
		입력값: @param id 사용자 아이디(String id), @param password비밀번호(String password)
		동작: users 테이블에서 아이디와 비밀번호가 일치하는 사용자를 조회
		반환값: @return 로그인 성공 시 User 객체, 실패 시 null
		public User login(String id, String password) throws Exception {
    		// 구현
		}
     */

	public User login(String id, String password) throws Exception {
		String sql = "select id, password, name, role from users "
				+ "where id = ? "
				+ "and password = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1,id);
				pstmt.setString(2,password);
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {  // 조건에 맞는 회원이 있으면
						User user = new User();  // User 객체 생성
						user.setId(rs.getString("id"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setRole(rs.getString("role"));
						return user;
					}
					return null;	// 로그인 실패
				}
		}
	}//end of method 3) login
	
	/**
	 * 4) 회원 정보 수정 - method updateUser
	 * User 클래스는 다음과 같은 필드를 갖고 있습니다.
	 *      String id
	 *      String password
	 *      String name
	 *      String role
	 *
	 * 다음 요구사항에 맞게 updateUser 메서드를 작성하세요.
	 *
	 * 입력값: @param user User 객체 (id, 변경할 password, 변경할 name을 포함해야 함)
	 * 동작: users 테이블에서 id가 일치하는 사용자의 비밀번호와 이름을 수정한다.
	 *   SQL 쿼리: "UPDATE users SET password = ?, name = ? WHERE id = ?"
	 * 반환값:
	 *   @return 업데이트된 레코드 수 (수정 성공 시 1, 실패 시 0)
	 * 예외처리:
	 *   @throws Exception 데이터베이스 연결 오류 또는 SQL 실행 오류 발생 가능성 있음
	 * 사용 예시:
	 *   User user = new User("hong123", "newpass", "홍길동", "user");
	 *   int result = updateUser(user);
	 *   // result == 1 이면 수정 성공, 0 이면 해당 id 없음
	 */

	public int updateUser(User user) throws Exception{
		String sql = "update users set password = ? , name = ?, where id = ?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1,  user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getId());
			return pstmt.executeUpdate();
		}
	}

	/**
     * 5) 회원 전체 조회 - method getAllUsers
     *
     * @return User 객체 리스트 (전체 회원)
     *
     * User 클래스는 다음과 같은 필드를 갖고 있습니다.
     *      String id
     *      String name
     *      String role
     *      Date createdAt
     *
     * 다음 요구사항에 맞게 getAllUsers 메서드를 작성하세요.
     *
     * 입력값:
     *   (없음)
     *
     * 동작:
     *   users 테이블에서 id, name, role, created_at 컬럼을 가져와
     *   생성일(created_at) 기준 내림차순으로 정렬한다.
     *   각 결과 행을 User 객체로 생성하여 리스트에 추가한다.
     *
     * 반환값:
     *   @return List<User> 객체 (DB에 저장된 모든 회원 정보를 담고 있음)
     *
     * 예외처리:
     *   @throws Exception 데이터베이스 연결 오류 또는 SQL 실행 오류 발생 가능성 있음
     *
     * 사용 예시:
     *   List<User> members = getAllUsers();
     *   for (User u : members) {
     *       System.out.println(u.getId() + " / " + u.getName() + " / " + u.getRole());
     *   }
     */

	public List<User> getAllUsers() throws Exception{
		String sql = "select id, name, role, create_at from users order by created_at desc";
		List<User> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while (rs.next()){
				User u = new User();
				u.setId(rs.getString("id"));
				u.setName(rs.getString("name"));
				u.setRole(rs.getString("role"));
				list.add(u);
			}
		}
		return list;
	}
	
    /**
     * 6) 로그인/로그아웃 이력 저장 - int type method insertLoginHistory
     *
     * login_history 테이블은 다음과 같은 컬럼을 갖고 있습니다.
     *      user_id   VARCHAR
     *      action    VARCHAR ('LOGIN' 또는 'LOGOUT')
     *      ip_address VARCHAR
     *      created_at TIMESTAMP (기본값: 현재 시간)
     *
     * 다음 요구사항에 맞게 insertLoginHistory 메서드를 작성하세요.
     *
     * 입력값:
     *   @param userId   사용자 아이디
     *   @param action   'LOGIN' 또는 'LOGOUT' 문자열
     *   @param ipAddress 접속한 IP 주소
     *
     * 동작:
     *   login_history 테이블에 user_id, action, ip_address 값을 추가로 저장한다.
     *
     * 반환값:
     *   @return int (INSERT 성공 시 1, 실패 시 0)
     *
     * 예외처리:
     *   @throws Exception 데이터베이스 연결 오류 또는 SQL 실행 오류 발생 가능성 있음
     *
     * 사용 예시:
     *   int result = insertLoginHistory("hong123", "LOGIN", "192.168.0.10");
     *   // result == 1 이면 기록 저장 성공
     */
	public int insertLoginHistory(String userId, String action, String ipAddress) throws Exception{
		String sql = " insert into login_history (user_id, action, ip_address) values (?,?,?)";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, userId);
			pstmt.setString(2, action);
			pstmt.setString(3, ipAddress);
			return pstmt.executeUpdate();
		}
	}
	
    /**
     * 7) 로그인 이력 조회 - method getLoginHistory
     *
     * login_history 테이블은 다음과 같은 컬럼을 갖고 있습니다.
     *      hist_id     BIGINT (PK)
     *      user_id     VARCHAR
     *      action      VARCHAR ('LOGIN' 또는 'LOGOUT')
     *      action_time TIMESTAMP
     *      ip_address  VARCHAR
     *
     * 다음 요구사항에 맞게 getLoginHistory 메서드를 작성하세요.
     *
     * 입력값:
     *   @param userId 조회할 사용자 아이디
     *                 (null 또는 빈 문자열이면 전체 이력을 조회)
     *
     * 동작:
     *   - userId가 주어지면 해당 사용자의 로그인/로그아웃 이력을 조회한다.
     *   - userId가 없으면 모든 사용자의 로그인/로그아웃 이력을 조회한다.
     *   - 결과는 최신 action_time 기준 내림차순으로 정렬한다.
     *   - 각 행을 Map<String, Object> 형태로 저장하여 리스트에 담는다.
     *
     * 반환값:
     *   @return List<Map<String, Object>> (로그인 이력 목록)
     *
     * 예외처리:
     *   @throws Exception 데이터베이스 연결 오류 또는 SQL 실행 오류 발생 가능성 있음
     *
     * 사용 예시:
     *   List<Map<String, Object>> history = getLoginHistory("hong123");
     *   for (Map<String, Object> row : history) {
     *       System.out.println(row.get("action_time") + " / " + row.get("action"));
     *   }
     */
    public List<java.util.Map<String, Object>> getLoginHistory(String userId) throws Exception {
        String sql;
        if (userId == null || userId.isEmpty()) {
            // 전체 로그인 이력 조회
            sql = "SELECT hist_id, user_id, action, action_time, ip_address FROM login_history ORDER BY action_time DESC";
        } else {
            // 특정 사용자 로그인 이력 조회
            sql = "SELECT hist_id, user_id, action, action_time, ip_address FROM login_history WHERE user_id = ? ORDER BY action_time DESC";
        }
        List<java.util.Map<String, Object>> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (userId != null && !userId.isEmpty()) pstmt.setString(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // 결과 행을 Map에 담아서 리스트에 추가
                    java.util.Map<String, Object> map = new java.util.HashMap<>();
                    map.put("hist_id", rs.getLong("hist_id"));
                    map.put("user_id", rs.getString("user_id"));
                    map.put("action", rs.getString("action"));
                    map.put("action_time", rs.getTimestamp("action_time"));
                    map.put("ip_address", rs.getString("ip_address"));
                    list.add(map);
                }
            }
        }
        return list; // 조회된 로그인 이력 반환
    }

}


























