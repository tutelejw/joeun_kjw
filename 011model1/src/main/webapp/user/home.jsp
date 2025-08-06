<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="spring.model1.service.user.vo.UserVO" %>

<!-- 
	1. 로그인 유무확인 :: Work Flow Control (방어적 코딩)  
		ㅇ 비 로그인 : 로그인 화면 display 
	   ㅇ 로  그  인 : 이미 로그인 한 회원임을 display
	2. 로그인 확인은...
		ㅇ 로그인한 회원은 session ObjectScope에 UserVO객체를 갖고, active 는 true
		ㅇ UserVO 객체의 유무 및 UserVO의 active 값 true/false  판단
-->

<%	UserVO userVO = (UserVO)session.getAttribute("userVO");	%>

<!-- 	#. 미 로그인한 회원 -->
<% if ( userVO == null  ||  ! userVO.isActive() )  { %>
			<jsp:forward page="logon.jsp" />
<% } %>

<!-- 	#. 로그인한 회원 -->
<html>
	<head></head>
	<body>
		<p>Simple Model2 Examples</p>
		<p> 환영합니다.  : <%= userVO.getUserId()%>님</p>
	</body>
</html>