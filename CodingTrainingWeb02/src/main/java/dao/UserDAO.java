package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;
import util.DBUtil;
import util.PasswordUtil;

public class UserDAO {
	// 아이디 중복 체크
	public boolean isIdExist(String id) throws Exception {
		String sql = "SELECT COUNT(*) FROM mini_user WHERE id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)){
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) > 0;
			}
			return true;
		}
		
	} //end of method isIdExist
	 // 회원가입 처리
	public int registerUser(User user)throws Exception{
		String sql = "insert into mini_user (id, password, age, name, role)"
				+ "values(?,?,?,?,?) ";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql)){
			String encryptedPassword = PasswordUtil.hashPassword(user.getPassword());
			psmt.setString(1, user.getId());
			psmt.setString(2, encryptedPassword);
			psmt.setInt(3, user.getAge());
			psmt.setString(4, user.getName());
			psmt.setString(5, user.getRole());
			return psmt.executeUpdate();			
		}
	}//end of method registerUser
	
	
	//로그인처리(비밀번호 검증) 사용자ID와Password를 매개변수로받아 검증하여 일치하면 User객체 or null반환
	public User login(String id, String password) throws Exception{
		String sql = "SELECT * FROM mini_user WHERE id = ?";
		try(Connection conn = DBUtil.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql)){
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				String storePassword = rs.getString("Password");
				if(PasswordUtil.verifyPassword(password, storePassword)) {
					return new User(
							rs.getString("id"), 
							rs.getString("password"), 
							rs.getString("name"), 
							rs.getInt("age"), 
							rs.getString("role"));
					}
				}
				return null;
			}
		}//end of method login
		
}
