package mini.project.d250806.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mini.project.d250806.dao.UserDAO;
import mini.project.d250806.model.User;


public class LoginServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
       // 로그인 폼에서 입력한 ID/PW 받아오기
       String id = request.getParameter("id");
       String pwd = request.getParameter("password");


       try {
           // DAO 객체 생성 및 로그인 메서드 호출
           UserDAO dao = new UserDAO();
           User user = dao.login(id, pwd);


           if (user != null) {
               // 로그인 성공 시 세션에 사용자 정보 저장
               HttpSession session = request.getSession();
               session.setAttribute("user", user);
               // 환영 페이지로 이동
               response.sendRedirect("welcome.jsp");
           } else {
               // 로그인 실패 시 로그인 페이지로 이동
               response.sendRedirect("login.jsp");
           }
       } catch (Exception e) {
           throw new ServletException(e);
       }
   }
}


