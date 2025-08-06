<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<body>
  <h2>Register</h2>
  <!-- <form action="register" method="post"> -->
  <form action="../RegisterServlet" method="post">
    ID: <input type="text" name="id"><br>
    Password: <input type="password" name="password"><br>
    Name: <input type="text" name="name"><br>
    <input type="submit" value="Register">
  </form>
  <a href="z_mini_jsp250806/login.jsp">Go to Login</a>
</body>
</html>
