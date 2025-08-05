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


public class LogoutServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
       // 기존 세션을 가져오고 무효화 (로그아웃 처리)
       HttpSession session = request.getSession(false);
       if (session != null) session.invalidate();
       // 로그인 페이지로 이동
       response.sendRedirect("login.jsp");
   }
}

