import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
*	FileName : ServletDeclaration.java
*  1. init(),service(),destroy() method 외에 필요한 Method Definition
*  2. Object Modeling
*      ==> attribute : Field(memberVariable)
*      ==> behavior : method
*/
public class ServletDeclaration extends HttpServlet	{
    
    ///service() method O/R
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		// 구구단을 출력하는 for문
		for(int i=0;i<10;i++){
			out.println("<< "+i+" >>단 출력<br/><hr/>");
			for(int j=0;j<10;j++){
				out.println(i+" X "+j+" = "+i*j+"<br/>");
			}
			out.println("<hr/>");
		}

    	out.println("<hr/><hr/> gugudan() method 사용 구구단 구현<hr/><hr/>");

		//method를 이용하여 구구단을 출력
		for(int i=0;i<10;i++){
			out.println("<< "+i+" >>단 출력<br/><hr/>");
			for(int j=0;j<10;j++){
				out.println(i+" X "+j+" = "+gugudan(i,j)+"<br/>");
			}
			out.println("<hr/>");
		}
	}

	//두수를 입력 받아 곱하고 값을 return하는  method
	public int gugudan(int i,int j){
		return i*j;
	}

}//end of class
