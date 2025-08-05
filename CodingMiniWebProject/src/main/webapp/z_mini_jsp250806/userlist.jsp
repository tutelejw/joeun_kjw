<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, mini.project.d250806.model.User" %>
<%
    List<User> users = (List<User>) request.getAttribute("users");
%>
<html>
<body>
  <h2>User List</h2>
  <table border="1">
    <tr><th>ID</th><th>Name</th></tr>
    <%
        for (User u : users) {
            out.println("<tr><td>" + u.getId() + "</td><td>" + u.getName() + "</td></tr>");
        }
    %>
  </table>
  <a href="welcome.jsp">Back</a>
</body>
</html>
