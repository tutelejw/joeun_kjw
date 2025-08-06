<%@ page contentType="text/html;charset=UTF-8" %>

<!-- ////////////////////////////////////////   변 경 된  부 분  //////////////////////////////////////////////////////////
	ㅇ Model2 Web Arch. 적용 시 ::  JSP는 View 역할  
 	ㅇ Work Flow Control 은 Controller 담당                                                  
 	ㅇ 아래의 주석 : Controller 담당하는 ControlServlet 에서 처리   
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->

<%
	//UserVO userVO = (UserVO)session.getAttribute("userVO");
%>

<html>
	<head></head>
	<body>

	<% //if(userVO == null ||  userVO.isActive() != true ) {%>
		
		<!-- ////////////////////////////////////////   변 경 된  부 분  //////////////////////////////////////////////////////////-->
		<!-- <form id="login" method="post" action="/011model1/user/logonAction.jsp">  -->
		<!--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
		<form id="login" method="post" action="/013model2/logonAction.do">

			아이디 : <input id="userId" type="text" name="userId" value=""><br/><br/>
			패스워드 : <input id="userPasswd" type="text" name="userPasswd" value=""><br/><br/>
			<input id="submit" type="submit" name="submit" value="Enter"/>

		</form>

	 <%//}else{ %>
			<%-- <%= userVO.getUserId() %>님은 이미 로그인 하셨습니다.  --%>
	<% //} %>

	</body>
</html>