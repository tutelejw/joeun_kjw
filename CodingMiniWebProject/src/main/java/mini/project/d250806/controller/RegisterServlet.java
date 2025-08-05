package mini.project.d250806.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mini.project.d250806.dao.UserDAO;
import mini.project.d250806.model.User;


public class RegisterServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
       // 회원가입 폼에서 전송된 파라미터 받기
       String id = request.getParameter("id");
       String pwd = request.getParameter("password");
       String name = request.getParameter("name");


       // 유저 객체 생성 (DTO 역할)
       User user = new User(id, pwd, name);


       try {
           // DAO 객체 생성
           UserDAO dao = new UserDAO();
           // 회원 등록 메서드 호출
           dao.registerUser(user);
           // 회원가입 성공 후 로그인 페이지로 이동
           response.sendRedirect("login.jsp");
       } catch (Exception e) {
           throw new ServletException(e);
       }
   }
}

