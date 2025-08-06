<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="mini.project.d250806.model.User" %>
<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) currentSession.getAttribute("user");
%>
<html>
<body>
  <h2>Welcome, <%= user.getName() %>!</h2>
  <a href="logout">Logout</a> | <a href="UserListServlet">User List (Admin)</a>
</body>
</html>
