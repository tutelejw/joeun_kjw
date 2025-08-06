package com.model2.mvc.framework;

//서블릿 관련 클래스들을 import (가져오기) 합니다.
//HTTP 요청과 응답을 다루기 위해 필요합니다.
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//추상 클래스 선언: 이 클래스는 직접 객체로 만들 수 없고, 상속해서 사용해야 합니다.
public abstract class Action {
    // 웹 애플리케이션의 전역 정보(설정, 리소스 등)에 접근할 수 있는 객체입니다.
	private ServletContext servletContext;
	// 기본 생성자 (아무 동작도 하지 않음)
	public Action(){
	}
	// servletContext 값을 외부에서 꺼낼 수 있게 해주는 메서드 (Getter)
	public ServletContext getServletContext() {
		return servletContext;
	}
	// servletContext 값을 외부에서 설정할 수 있게 해주는 메서드 (Setter)
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
    // 이 메서드는 반드시 자식 클래스에서 구현해야 합니다.
    // 사용자의 요청(HttpServletRequest)과 응답(HttpServletResponse)을 받아서,
    // 처리 후 이동할 JSP 페이지 등의 경로(String)를 리턴합니다.
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}