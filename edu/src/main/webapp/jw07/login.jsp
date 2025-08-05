<%@ page import="jw.service.user.dao.UserDao" %>
<%@ page import="jw.service.user.vo.UserVO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>

<%
    // 폼에서 보낸 값 받기
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");

    UserDao dao = new UserDao();
    boolean success = dao.login(id, pwd);
%>

<html>
<head><title>로그인 결과</title></head>
<body>
<%
    if (success) {
%>
        <h2>환영합니다, <%= id %>님!</h2>
<%
    } else {
%>
        <h2>로그인 실패. 다시 시도해주세요.</h2>
<%
    }
%>
</body>
</html>
