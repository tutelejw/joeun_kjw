package jw02;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * FileName : MultiCheck01.java
 */
//@WebServlet("/MultiCheck01")
public class MultiCheck01 extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
		/*================================================
		//==> Tomcat 8.X 이상 Get 방식 한글해결방법
		//==> servev.xml 설정 변경
		<Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" 
			   useBodyEncodingForURI="true"/>   <== 추가
		====================================================*/
		req.setCharacterEncoding("UTF-8");
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<boby><center><h2>Client에서 전송된 내용</h2></center><p>");

		String name =req.getParameter("name");
		String addr = req.getParameter("addr");
	
		out.println("<li>이름 : "+ name);
		out.println("<li>주소 : "+ addr);
		
		///*//[처리방법 1]  sw 는 checkbox  다중 선택이 가능, getParameter()로 처리한 경우
         String sw = req.getParameter("sw");
         out.println("<li>사용중인  SW : "+ sw);
        //*/
        /* //[처리방법 2]  1. sw 는 checkbox  여러개의 value 갖을수 있다.
        //                         2. 다중 선택일 경우 array 을 return 하는 getParameterValues() 사용.
          	out.println("<br>선택하신 sw아래와 같습니다.<br>");
            String[] sw = req.getParameterValues("sw");

            for(int i=0;i<sw.length;i++){
                out.println("<li>"+sw[i]);
            }
         */
		out.println("<li>사용중인 OS : "+  req.getParameter("os"));
		out.println("<li>사용중인 cpu : "+ req.getParameter("cpu"));
		out.println("</body></html>");
	}
	
}//end of class
