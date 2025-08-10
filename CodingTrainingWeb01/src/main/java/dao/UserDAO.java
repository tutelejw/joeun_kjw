package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
     * 3) 로그인 - 입력한 아이디와 비밀번호가 맞으면 User 객체 반환
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
	}//end of method login
	
}


























