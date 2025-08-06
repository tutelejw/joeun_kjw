package spring.model2.control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * FileName : ControlServlet
 * ㅇ 단일인입점(Single Point of Entry)
 * ㅇ Client 요구사항 판단(?)
 * ㅇ 선처리 / 공통처리
 * 		- Work Flow Control :: 권한,인증 등..
 * 		- Client Form Data 한글 처리  
 * ㅇ Business logic 수행 ( Bean Method Call)
 * ㅇ Model 과 View 연결 
 * 		- Business Logic 처리 결과  JSP 전달 (Object Scope / VO 사용 )  
 * 	ㅇ 처리된 결과에 따라, JSP 로 forward /sendRedirect  : Navigation
 */
public class DispatcherServlet extends HttpServlet{

	///init() Method
	public void init(ServletConfig sc) throws ServletException{
		super.init(sc);
		//==> web.xml 설정:: <load-on-startup>1</load-on-startup> 확인
		System.out.println("\n\n========================");
		System.out.println("ControlServlet의 init() Method");
		System.out.println("========================\n");
	}

	///service() Method
	public void service(HttpServletRequest req,HttpServletResponse res)
		                                                          throws ServletException,IOException{
		
		System.out.println("\n[ DispatcherServlet.service() start........]");
		
		//==> CONTROLLER :: Client 요구사항 판단 ::  URL/URI 이용
		String actionPage = this.getURI( req.getRequestURI() );
		System.out.println("::URI ?  =>: "+req.getRequestURI());
		System.out.println("::client의 요구사항은 ?  =>: "+actionPage);
		
		//==> CONTROLLER :: 선처리 / 공통처리 사항이 있다면..
		//==> 본 예제 : 한글처리 / 
		//req.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("euc-kr");
		//HttpSession session = req.getSession(true);   <---?? 세션 default??라서?? 안써서??
		
		//==> CONTROLLER :: Client 요구사항 처리 및 Business logic 처리
		// Business Logic 을 처리하는 Controller 선언
		Controller controller = null;
		
		ControllerMapping cf = ControllerMapping.getInstance();
		controller = cf.getController(actionPage);
		
		controller.execute(req,res);
		
		System.out.println("[ DispatcherServlet.service() end........]");
		
	}//end of service
	
	//client 의 요구사항 판단.  ==>requestURI parsing 파싱= "/~~.do" 형식이므로....
	private String getURI(String requestURI){
		int start = requestURI.lastIndexOf('/')+1;
		int end = requestURI.lastIndexOf(".do");
		String actionPage = requestURI.substring(start,end);
		System.out.println("dispatcher- actionPage"+actionPage);
		return 	actionPage;
	}

}//end of class












