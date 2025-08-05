<%@ page import="jw.service.user.dao.UserDao" %>
<%@ page import="jw.service.user.vo.UserVO" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>

<%
	UserVO userVO =(UserVO)session.getAttribute("userVO");
	if(userVO == null ){
		userVO = new UserVO();
	}
%>

<% System.out.println(" isActive 값 확인 : " + userVO.isActive()); %>

<% if( ! userVO.isActive() ) { %>
<jsp:include page = "../jw07/login.html"/>
<% System.out.println(" true : " + userVO.isActive());  %>
<%}else {%>
<%
System.out.println(" false : " + userVO.isActive());
//1.session 을 종료
//session.invalidate();

//2.login정보를 갖는 userVO session ObjectScope remove
//session.removeAttribute("userVO");

//3. login 정보를 갖는 userVO Field active false
userVO.setActive(false);

// logout 후 회원가입 page, 로 sendRedirect
response.sendRedirect("/edu/jw07/addUser.html");
%>
<% } %>

