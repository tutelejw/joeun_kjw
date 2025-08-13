<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>

<h3> JSTL c:if을 사용</h3>
<c:if test="true"> if문 내부 1<br/></c:if>
<h3> JSTL c:if을 사용</h3>
<c:if test="false"> if문 내부 2<br/></c:if>

<h3> :: Client Form Data 처리</h3>
1.이름 : <%= request.getParameter("name") %> <br/>
1.이름 :${param.name } <br/>

<c:if test = "${param.name == '홍길동' } ">
3.홍길동 님 환영 합니다.<br/>
</c:if>

<c:if test = "${!empty param.name }">
4.${param.name} 님 환영 합니다. <br/>
</c:if>

<c:set var="level"  value="성인" scope="session"/>
<c:if test="${param.age < 19}">
<c:set var = "level" value="청소년" />
</c:if>
5. ${param.name} 님은 나이 ${param.age} 로 ${level} 입니다. <br/>
확인1: ${sessionScope.level}<br/>
확인2: ${pageScope.level}<br/>

<c:if test ="${! empty paramValues.sw[0] }">
6. 선택하신 sw :  ${paramValues.sw[0] }<br/>
</c:if>
<c:if test ="${! empty paramValues.sw[1] }">
6. 선택하신 sw :  ${paramValues.sw[1] }<br/>
</c:if>
<c:if test ="${! empty paramValues.sw[2] }">
6. 선택하신 sw :  ${paramValues.sw[2] }<br/>
</c:if>

<c:choose>
	<c:when test = "${param.age >19 }">
		7.${param.name } 님은 성인으로 나이 : ${param.age } <br/>


