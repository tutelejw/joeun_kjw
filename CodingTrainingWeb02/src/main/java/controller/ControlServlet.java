package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.____LogoutAction;
import action.RegisterAction;
import action._____LoginAction;

//@WebServlet("*.do")
public class ControlServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) 
			throws ServletException, IOException {
		processRequest(req, rep);
	}//end of method doGet 
	
	protected void doPost(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException{
		processRequest(req, rep);
	} // end of method doPost
	
	private void processRequest(HttpServletRequest req, HttpServletResponse rep)
			throws ServletException, IOException{
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1);
		
		try {
			switch(action) {
			case "register.do" :
				new RegisterAction().execute(req, rep);
				break;
//			case "login.do":
//				new _____LoginAction().execute(req, rep);
//				break;
//			case "logout.do":
//				new ____LogoutAction().execute(req, rep);
//				break;
			default:
				rep.sendRedirect("/error.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
			rep.sendRedirect("/error.jsp");
		}
	} //end of method processRequest
}//end of class
