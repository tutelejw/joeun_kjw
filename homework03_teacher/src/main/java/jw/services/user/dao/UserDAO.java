package jw.services.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jw.common.pool.OracleConnectionPool;
import jw.services.user.vo.UserVO;
/* 
 * FileName : UserDAO.java
 * ㅇ AbstarctDAO공유하면 회원관리 Service를 담당하는 DAO
*/
public class  UserDAO{
	///Field 
	
	///constructor
	public UserDAO(){
	}

	//DB에서 Data를 insert 하는method
	public void addUser(UserVO userVO){
		//JDBC을 이용하기 위한 객체 생성
		Connection con = null;
		PreparedStatement pStmt = null;
		try{		
			//1단계 connetion하기(login과정)
			con = OracleConnectionPool.getInstance().getConnection();
			//2단계 insert query 문을 전송하는단계 
			pStmt = con.prepareStatement(	"insert " +
																"into users ( no, id, pwd) " +
																"values( ? , ? , ? )" );
			pStmt.setInt(1,userVO.getNo());
			pStmt.setString(2,userVO.getId());
			pStmt.setString(3,userVO.getPwd());

			//3단계 결과값 확인하기 => DB에 data insert 유무확인 ...
			if( 1 == pStmt.executeUpdate()){    
				userVO.setActive(true);
			}
		}catch(Exception e){		
			e.printStackTrace();
		}finally{
			//각각의 DB와 관련된 객체 close
			if(pStmt != null){
				try{	
					pStmt.close();	
				}catch(Exception e2){  }
			}
			if(con != null){
				try{	
					con.close();	
				}catch(Exception e3){  }
			}
		}
	}//end of addUser()
	
	//회원정보(회원목록)를 select 하여 UserVO 로 return 하는 method
	public UserVO findUser(String id){
		//JDBC을 이용하기 위한 객체 생성
		UserVO userVO = new UserVO();
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try{		
			//1단계 connetion하기(login과정)
			con = OracleConnectionPool.getInstance().getConnection();

			//2단계 query(select id,pwd from users)문을 전송하는단계 
			pStmt = con.prepareStatement("select no,id,pwd from users where id=?");
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();
			//3단계 결과값 확인하기
			//==> 1. select한 결과 UserVO객체화
			//==> 2. 회원정보를 갖는 UserVO 객체 ArrayList에저장
			if(rs.next()){    
				userVO.setNo( rs.getInt("no") );
				userVO.setPwd( rs.getString("pwd") );
				userVO.setId( rs.getString("id") );
				//==> userVO의 내용을 확인하면...(console 확인요망...)
				System.out.println(userVO);
				//==> arrayList에 UserVO객체 add
			}
		}catch(Exception e){		
			e.printStackTrace();
		}finally{
			//각각의 DB와 관련된 객체 close
			if(rs != null){
				try{	
					rs.close();	
				}catch(Exception e1){  }
			}
			if(pStmt != null){
				try{	
					pStmt.close();	
				}catch(Exception e2){  }
			}
			if(con != null){
				try{	
					con.close();	
				}catch(Exception e3){  }
			}
		}
		//==> 회원목록 정보를 갖는 ArrayList객체 return 
		return userVO;
	}//end of getUser()


}//end of class	