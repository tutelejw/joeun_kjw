<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    //JSP 파일에서는 session은 JSP 내장 객체이므로 재선언하지 말고 바로 사용하세요.
	//javax.servlet.http.HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("loginForm.do");
        return;
    }
    User user = (User) session.getAttribute("user");

%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>내 정보 수정</title></head>
<body>
  <h2>내 정보 수정</h2>
  <form action="edit.do" method="post">
    ID: <input type="text" name="id" value="<%= user.getId() %>" readonly><br>
    Password: <input type="password" name="password" value="<%= user.getPassword() %>"><br>
    Name: <input type="text" name="name" value="<%= user.getName() %>"><br>
    <input type="submit" value="수정">
  </form>
  <a href="welcome.do">돌아가기</a>
</body>
</html>
