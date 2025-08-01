package jw05;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * FileName : CookieWriterToClient.java
 *	:: Client 에 필요정보를 저장하는 Cookie 사용
*/

public class LoginBeanDataSourceSession extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		HttpSession session = req.getSession(true);
		
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		System.out.println("session에 저장된 UserVO 유무 확인 : " +  userVO);
		
		if( !(id == null || id.equals(""))) {
			userVO=new UserVO();
			userVO.setId(id);
			userVO.setPwd(pwd);
			UserPoolDao userPoolDao = new UserPoolDao();
			userPoolDao.getUser(userVO);
		}
        out.println("<html><head></head>");
        out.println("<body>");
        out.println("<h2> 로그인 화면 </h2>");

        //if( userVO != null && userVO.getActive() =="Y" ) {
        if( userVO != null ) {
        	out.println(userVO.getId() +"환영함다");
        	session.setAttribute("userVO", userVO);
        }else {
        	out.println("로그인실패");
        	System.out.println("로그인실패");
        }
        out.println("<p><p><a href='/edu/jw05/loginBeanDataSourceSession.html'> 뒤로 </a>");
		out.println("</body></html>");
	}

}//end of class
