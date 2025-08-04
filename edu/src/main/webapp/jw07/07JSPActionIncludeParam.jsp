<%@ page contentType="text/html;charset=UTF-8" %>

<br/><hr/>

	<h3>
	 ㅇ 07JSPActionIncludeParam.jsp 시작<br/><br/>
	 
	 	request,session,application  Object Scope  Data 추출 <br/><br/>
	
		request ObjectScope 추출 Data : <%= request.getAttribute("aaa") %><br/>
		session ObjectScope 추출 Data : <%= session.getAttribute("bbb") %><br/>
		application ObjectScope 추출 Data : <%= application.getAttribute("ccc") %><br/><br/>
		
		request ObjectScope 추출 Data : <%= request.getAttribute("ddd") %><br/>
	
	 ㅇ 07JSPActionIncludeParam.jsp 끝 <br/> 
	</h3>
	
	<h1>
	<%--	<jsp:param ~~ /> 전달 Data 추출 API 확인 --%>
	param으로 넘어온 str값 : <%= request.getParameter("str")%><br/><br/>
	</h1>

<hr/><br/>