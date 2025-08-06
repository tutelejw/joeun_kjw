package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

//모든 요청을 한 곳에서 받아서, 요청에 맞는 Action 클래스로 전달하고, 처리 결과에 따라 이동(포워드 또는 리다이렉트)시키는 역할
//ActionServlet은 모든 요청을 받아 처리하는 "Front Controller"입니다.
//웹 애플리케이션의 진입점 역할을 합니다.
public class ActionServlet extends HttpServlet {
	// 요청 경로(URL)에 따라 어떤 Action을 실행할지 매핑해주는 객체
	private RequestMapping mapper;
	// 서블릿이 처음 실행될 때 호출되는 초기화 메서드입니다.
	@Override
	public void init() throws ServletException {
		super.init(); // 부모 클래스의 초기화도 함께 수행
		// web.xml에서 <init-param>으로 전달된 설정값("resources")을 읽음
		String resources=getServletConfig().getInitParameter("resources");
		// 해당 설정값을 기반으로 RequestMapping 객체를 초기화 (싱글톤 방식)
		mapper=RequestMapping.getInstance(resources);
	}

	// 클라이언트 요청이 들어올 때마다 실행되는 메서드 (GET, POST 관계없이 처리)
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		// 사용자가 요청한 전체 URI (예: /myApp/product/list.do)
		String url = request.getRequestURI();
		// 현재 프로젝트의 context path (예: /myApp)
		String contextPath = request.getContextPath();
		// contextPath 이후의 실제 요청 경로만 추출 (예: /product/list.do)
		String path = url.substring(contextPath.length());
		System.out.println(path);// 요청 경로 로그 출력 (디버깅용)
		
		try{
			// 요청 경로에 해당하는 Action 클래스를 매핑에서 찾음
			Action action = mapper.getAction(path);
			// Action에게 현재 웹 애플리케이션의 Context 정보를 전달
			action.setServletContext(getServletContext());
			
			// 실제 요청 처리 (execute 메서드 실행) → 처리 결과로 이동할 페이지 경로 리턴
			// 예: "forward:/user/welcome.jsp" 또는 "redirect:/login.jsp"
			String resultPage=action.execute(request, response);
			// 실제 이동할 페이지 경로만 추출 (앞의 "forward:" 또는 "redirect:" 제거)
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			// forward 인지 redirect 인지에 따라 다르게 처리
			if(resultPage.startsWith("forward:"))
				// 서버 내부에서 이동 (요청 정보 유지)
				HttpUtil.forward(request, response, result);
			else
				// 클라이언트에게 새로운 요청을 보내게 함 (요청 정보 초기화됨)
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			// 예외 발생 시 콘솔에 출력 (디버깅용)
			ex.printStackTrace();
		}
	}
}