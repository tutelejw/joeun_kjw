// /framework/ActionServlet.java

package com.semi.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.common.util.HttpUtil;

public class ActionServlet extends HttpServlet {
	
	///Field
	private RequestMapping requestMapping;
	
	///Method
	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		requestMapping=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		// ActionServlet.service(...) 내부: path 계산부 교체
		String ctx = request.getContextPath();
	
		// include/forward 우선
		String includePath = (String) request.getAttribute("javax.servlet.include.servlet_path");
		String forwardPath = (String) request.getAttribute("javax.servlet.forward.servlet_path");
		String servletPath = request.getServletPath();
		String uri        = request.getRequestURI();
	
		String reqeustPath;
		if (includePath != null) {
			reqeustPath = includePath;                    // <c:import> 같은 include 시: /listComment.do
		} else if (forwardPath != null) {
			reqeustPath = forwardPath;                    // forward 시
		} else if (servletPath != null && !servletPath.isEmpty()) {
			reqeustPath = servletPath;                    // 평소 *.do 매핑
		} else {
		    // 최후의 수단: URI에서 컨텍스트 제거
			reqeustPath = (ctx != null && !ctx.isEmpty() && uri.startsWith(ctx)) ? uri.substring(ctx.length()) : uri;
		}
	
		System.out.println("ActionServlet.service() resolved path : " + reqeustPath);
		
		try{
			Action action = requestMapping.getAction(reqeustPath);
			action.setServletContext(getServletContext());
			
			String resultPage=action.execute(request, response);

			// ========================[수정된 부분 시작]========================
			// Action의 execute() 메서드가 null을 반환하면,
			// 해당 Action이 이미 응답(JSON 등)을 직접 처리했음을 의미합니다.
			// 따라서 서블릿은 포워딩이나 리다이렉션 없이 처리를 종료합니다.
			if (resultPage == null) {
				System.out.println("---ActionServlet execute null return---");
				return;
			}
			// ========================[수정된 부분 끝]==========================

			String path=resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:")){
				HttpUtil.forward(request, response, path);
			}else{
				HttpUtil.redirect(response, path);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}