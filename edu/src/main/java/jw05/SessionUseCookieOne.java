package jw05;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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

public class SessionUseCookieOne extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		HttpSession session = req.getSession(true);
		
		if(session.isNew()) {
			session.setAttribute("name",  new String("홍길동"));
		}
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

        out.println("<html><head></head>");
        out.println("<body>");
        out.println("<h2> SessionUseCookieOne </h2>");

    	out.println("<hr> jsessionid = "+ session.getId() + "<hr>");
    	System.out.println("jsessionid = "+ session.getId());
        
        if(session.isNew()) {
        	out.println("세션이 새로 생성됨 <br>");
        }else {
        	out.println("<hr> jsessionid = "+ session.getId() + "<hr>");
        }
        out.println("<hr>");
        out.println("<a href='/edu/SessionUseCookieTwo'> 링크 </a>");
        out.println("</body></html>");
	}

}//end of class
