package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

public class RegisterAction {
	public void execute(HttpServletRequest req, HttpServletResponse rep) 
			throws Exception, ServletException, IOException{ 
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		
		User user = new User(id, password, name, age, "USER"); // 끝에 USER는 role 회원가입시는 무조건 일반유저라서 고정인거지..
		UserDAO userDAO = new UserDAO();
		
		//회원가입처리
		int result = userDAO.registerUser(user);
		if(result > 0) {
			rep.sendRedirect("/loginForm.do");
		}else {
			rep.sendRedirect("/registerForm.do");			
		}
	}// end of method execute
}
