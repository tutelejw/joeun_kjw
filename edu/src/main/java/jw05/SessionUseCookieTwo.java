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

public class SessionUseCookieTwo extends HttpServlet{

	public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();

		// Client 로 부터 전송된 Cookie 처리
		Cookie[] cookies = req.getCookies();

		//Cookie 의 존재유무 및 name=value 처리
		if(cookies != null) {
			System.out.println("Client에서 전송된 Cookie 있습니다.<br/>");
			out.println("Client에서 전송된 Cookie 있습니다.<br/>");
			//Array 로 return  :: Array 갯수만큼 처리
			for(int i=0;i<cookies.length;i++){
				//name=value 형식의 저장값 중 name 추출
				String name = cookies[i].getName();
				String value = URLDecoder.decode(cookies[i].getValue(), StandardCharsets.UTF_8);
				System.out.println("client로 부터 전송된 cookie : "+name+"="+value);
				System.out.println("");
			}
		}else{
			System.out.println("Client에서 전송된 cookie가 없습니다.");
            out.println("Client에서 전송된 cookie가 없습니다.<br/>");
        }
				
		HttpSession session = req.getSession(false);
		
        out.println("<html><head></head>");
        out.println("<body>");
        out.println("<h2> SessionUseCookieTwo </h2>");

        if(session != null) {
        	out.println("<hr> jsessionid = "+ session.getId() + "<hr>");
        	String name = (String)session.getAttribute("name");
        	out.println("이름 : " + name);
        }else {
        	out.println("처음임다");
        }
        out.println("</body></html>");
	}

}//end of class
