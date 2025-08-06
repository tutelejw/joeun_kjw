package spring.model2.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//FileName Controller.java
//controller 객체의 일반적, 공통적 행위(method)을 정의 규정하는 interface
//controller 객체는 Control 에서 수행하는 실질적, 핵심적 역활을 수행
//Servlet API 핵심, 주요한 객체 HttpServletRequest req,HttpServletResponse res 인자로 전달 받음

public interface Controller{

	public void execute(HttpServletRequest req,HttpServletResponse res)
		                                                          throws ServletException,IOException;
}//end of class












