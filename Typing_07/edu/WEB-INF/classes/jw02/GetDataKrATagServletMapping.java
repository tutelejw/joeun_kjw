package jw02;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class GetDataKrATagServletMapping extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

		/*================================================
		//==> Tomcat 8.X 이상 Get 방식 한글해결방법
		//==> servev.xml 설정 변경
		<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" 
			   useBodyEncodingForURI="true"/>   <== 추가
		====================================================*/
		req.setCharacterEncoding("UTF-8");

		//아래의 두실행문은  servlet에서 client로 Html을 전송시 반드시 코딩
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		//client에서 전송되어 오는 data(QueryString :: name=value)처리 (API확인)
    String clientName = req.getParameter("name");
		String clientAddr = req.getParameter("addr");
        
		//client로 부터 받은 Data를 출력(출력은 어디로 ??)
    System.out.println(clientName+" : "+clientAddr);

		out.println("<html>");
		out.println("<head><title>GetDataKrATag.java</title></head>");
		out.println("<body>");

		out.println("<h2>Get Test </h2>");
		out.println("<li> 이름 : "+clientName);
		out.println("<li> 주소 : "+clientAddr);
		
		out.println("<p><p><a href='/edu/jw02/getDataKrATagServletMapping.html'>뒤로</a>");
		
		//==> <a> 태를 이용하여  Query문자열 전달
		out.println
		("<p><a href='/edu/GetDataKrATagServletMapping?name=홍길동&addr=서울'>" +
                                                                                                                      "자기자신</a>");
		out.println("</body>");
		out.println("</html>");
	}

}//end of class
