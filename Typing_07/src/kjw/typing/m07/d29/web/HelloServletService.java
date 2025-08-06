package kjw.typing.m07.d29.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServletService extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 응답 콘텐츠 타입 설정 (한글 처리를 위해 UTF-8)
        resp.setContentType("text/html; charset=UTF-8");
        // 출력 스트림
        resp.getWriter().println("Korea, 안녕 HelloServlet");
    }
}

//HelloServletService.java 구현
//1. httpServlet상속
//2.service() method OverRding
//3.브라우저 화면에 Korea, 안녕 HelloServlet출력
//4.web.xml 설정
//FQCN HelloServletService.class
//URL /HelloServletService