<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<body>
  <h2>Login</h2>
  <form action="../LoginServlet" method="post">
    ID: <input type="text" name="id"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Login">
      <br/><br/><a href="register.jsp">Go to 회원가입</a>
  </form>
</body>
</html>
 