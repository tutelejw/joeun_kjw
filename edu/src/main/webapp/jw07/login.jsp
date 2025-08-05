<%@ page import="jw.service.user.dao.UserDao" %>
<%@ page import="jw.service.user.vo.UserVO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>

<%
    // 폼에서 보낸 값 받기
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");

    UserVO userVO = new UserVO();
	userVO.setId(id);
	userVO.setPwd(pwd);
	
	UserDao userDAO = new UserDao();
	userDAO.getUser(userVO);
%>

<html>
<head><title>로그인 결과</title></head>
<body>
<%
    if (userVO.isActive()){ %>
    <%= id %> 님 환영 합니다. 
    <% session.setAttribute("userVO", userVO);  %>
    <%     } else {  %>
        <h2>로그인 실패. id pwd를 확인 후 다시 시도해주세요.</h2>
<%    } %>
<p/><p/><a href='/edu/jw07/login.html'> login.html 로 이동 </a>
</body>
</html>
