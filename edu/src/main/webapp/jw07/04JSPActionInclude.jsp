<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<body>

::  04JSPActionInclude.jsp 시작 <br/>

<%
	// Scriptlet tag 변수 선언 :: service() Method  local variable
	String value = "local variable";

	// request,session,application  ObjectScope  Data 저장
	request.setAttribute("aaa",new String("request ObjectScope 저장된 문자열"));
	session.setAttribute("bbb",new String("session ObjectScope 저장된 문자열"));
	application.setAttribute("ccc",new String("application ObjectScope 저장된 문자열"));
%>

<jsp:include  page="05JSPActionInclude.jsp"/>

::  04JSPActionInclude.jsp 끝 <br/>

</body>
</html>