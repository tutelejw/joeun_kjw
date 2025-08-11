package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

/**
 * 📌 Front Controller 역할을 하는 서블릿
 *  - web.xml에 *.do로 매핑되어 있어 모든 .do 요청을 처리함
 *  - 요청 URI에 따라 적절한 Action 클래스를 실행하고, forward 또는 redirect로 결과 View로 이동
 */
public class ActionServlet extends HttpServlet {

	// RequestMapping은 요청 URL(String) → Action 인스턴스를 매핑 관리하는 클래스
	private RequestMapping mapper;

	/**
	 * ✅ 서블릿 초기화 메서드 (서버가 켜질 때 1번 실행)
	 *  - web.xml에서 init-param으로 전달된 'resources' 값 읽음
	 *  - 해당 값은 보통 actionmapping.properties 파일 경로임
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		
		// web.xml의 <init-param name="resources" value="actionmapping.properties"> 읽어오기
		String resources = getServletConfig().getInitParameter("resources");

		// RequestMapping 인스턴스를 생성하고 properties 파일 로드
		mapper = RequestMapping.getInstance(resources);
	}

	/**
	 * ✅ 클라이언트 요청 처리 메서드
	 *  - 모든 HTTP 요청 (GET/POST 등)을 처리함
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 전체 요청 URI → 예: /model2mvc/product/listProduct.do
		String url = request.getRequestURI();

		// 현재 웹 애플리케이션의 context path → 예: /model2mvc
		String contextPath = request.getContextPath();

		// context path 이후의 경로만 추출 → 예: /product/listProduct.do
		String path = url.substring(contextPath.length());
		System.out.println("요청 path : " + path);

		try {
			// ✅ 요청 URL에 대응하는 Action 객체를 RequestMapping에서 얻어옴
			Action action = mapper.getAction(path);

			// ServletContext를 Action에 전달 (필요한 경우 활용 가능)
			action.setServletContext(getServletContext());

			// ✅ Action 클래스의 execute 메서드를 호출하여 실제 로직 수행
			//    → 결과로 이동할 경로를 문자열로 반환 (예: forward:/product/listProduct.jsp)
			String resultPage = action.execute(request, response);

			// 반환된 문자열에서 콜론(:) 이후만 추출 → 실제 경로 부분
			String result = resultPage.substring(resultPage.indexOf(":") + 1);

			// ✅ 결과가 "forward:"로 시작하면 서버 내부에서 JSP로 포워딩
			if (resultPage.startsWith("forward:")) {
				HttpUtil.forward(request, response, result);

			// 그렇지 않으면 클라이언트에게 리다이렉트 응답
			} else {
				HttpUtil.redirect(response, result);
			}

		} catch (Exception ex) {
			// 예외 발생 시 스택트레이스 출력
			ex.printStackTrace();
		}
	}
}
