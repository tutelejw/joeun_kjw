<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8"></head>
<body>
	::10ResponseSendredirect.jsp 시작 <br/>
	
	<% request.setAttribute("aaa", new String("연결됨")); %>
	
	<% response.sendRedirect("09JSPActionForward.jsp"); %>
	
	<% //
	response.sendRedirect("http://127.0.0.1:8080/edu/jw07/09JSPActionForward.jsp"); %>
	<% // response.SendRedirect("http://www.daum.net"); %>
	
	::10ResponseSendredirect.jsp 끝 <br/>
</body>
</html>