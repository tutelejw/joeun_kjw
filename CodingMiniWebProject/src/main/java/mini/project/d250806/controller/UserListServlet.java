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
import java.util.*;
import mini.project.d250806.dao.UserDAO;
import mini.project.d250806.model.User;


public class UserListServlet extends HttpServlet {
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException {
       try {
           // DAO를 통해 전체 회원 리스트 가져오기
           UserDAO dao = new UserDAO();
           List<User> users = dao.getAllUsers();


           // request 객체에 users 속성 저장 후 포워드
           request.setAttribute("users", users);
           RequestDispatcher dispatcher = request.getRequestDispatcher("userlist.jsp");
           dispatcher.forward(request, response);
       } catch (Exception e) {
           throw new ServletException(e);
       }
   }
}

