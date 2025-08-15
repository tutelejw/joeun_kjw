<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Map<String, Object>> history = (List<Map<String, Object>>) request.getAttribute("history");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>로그인 이력</title></head>
<body>
  <h2>로그인 이력</h2>
  <table border="1">
    <tr><th>번호</th><th>유저ID</th><th>동작</th><th>시간</th><th>IP</th></tr>
    <% for (Map<String, Object> h : history) { %>
      <tr>
        <td><%= h.get("hist_id") %></td>
        <td><%= h.get("user_id") %></td>
        <td><%= h.get("action") %></td>
        <td><%= h.get("action_time") %></td>
        <td><%= h.get("ip_address") %></td>
      </tr>
    <% } %>
  </table>
  <a href="welcome.do">돌아가기</a>
</body>
</html>
