<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 관리자만 접근한다고 가정 (ControlServlet에서 이미 체크함)
    List<User> users = (List<User>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>회원 목록</title></head>
<body>
  <h2>회원 목록 (관리자)</h2>
  <table border="1">
    <tr><th>ID</th><th>이름</th><th>권한</th></tr>
    <% for (User u : users) { %>
      <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getName() %></td>
        <td><%= u.getRole() %></td>
      </tr>
    <% } %>
  </table>
  <a href="welcome.do">돌아가기</a>
</body>
</html>
