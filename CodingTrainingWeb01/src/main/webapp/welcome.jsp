<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 세션에서 사용자 정보 가져오기
    //jsp 에서는 session은 JSP 내장 객체이므로 재선언하지 말고 바로 사용하세요.
    //javax.servlet.http.HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
        // 로그인 되어있지 않으면 로그인 페이지로 리다이렉트
        response.sendRedirect("loginForm.do");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>환영합니다</title></head>
<body>
  <h2>환영합니다, <%= user.getName() %> 님!</h2>
  <p>접속 ID: <%= user.getId() %></p>
  <p>권한: <%= user.getRole() %></p>

  <a href="editForm.do">내 정보 수정</a> |
  <a href="loginHistory.do">로그인 이력</a> |
  <% if ("ADMIN".equalsIgnoreCase(user.getRole())) { %>
    <a href="userList.do">회원 목록 (관리자)</a> |
  <% } %>
  <a href="logout.do">로그아웃</a>
</body>
</html>
