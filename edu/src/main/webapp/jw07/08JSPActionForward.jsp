<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8"></head>
<body>
	::08JSPActionForward.jsp 시작 <br/>
	<% request.setAttribute("aaa", new String("연결됨")); %>
	
	<jsp:forward page="09JSPActionForward.jsp" />
	
	::08JSPActionForward.jsp 끝 <br/>
</body>
</html>