<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% request.setCharacterEncoding("UTF-8"); %>

<h3> :: c:forEach 이용 구구단 출력</h3>

1. c:forEach 이용 구구단을 출력 <br/>

<c:forEach var="i" begin="1" end="10" step="1">
 5* ${i} = ${5*i} <br/>
 </c:forEach><br/><hr/>
 
2.중첩 c:forEach 이용 : 구구단 짝수 출력<br/>
<c:forEach var="i" begin="2" end="9" step="2">
[${i }]단을 출력합니다. <br/>
	 <c:forEach var="j" begin="1" end="10" step="1">
	 ${i}* ${j} = ${i*j} <br/>
	 </c:forEach><br/><hr/>
 </c:forEach><br/><hr/>
 
<h3> 3.index 관리 Collection 접근</h3> 
<%
	java.util.Vector vector= new java.util.Vector();
	vector.add("A");
	vector.add("B");
	vector.add("C");
	vector.add("D");
	
	//console 확인 후 아래의 ForEach와 비교
	System.out.println("==================");
	System.out.println("java for문 출력 부분");
	System.out.println("==================");	
%>

<c:forEach var="i"  items="<%=vector%>" begin="0" end="10" step="1">
 	:: vector 저장정보 추출 : ${i}<br/>
 </c:forEach><br/><hr/>
<c:forEach var="i" items="<%=vector%>" begin="1"  step="1">
 	:: vector 저장정보 추출 : ${i}<br/>
 </c:forEach><br/><hr/>
 
<c:forEach var="i"  items="<%=vector%>" begin="0" step="2">
 	:: vector 저장정보 추출 : ${i}<br/>
 </c:forEach><br/><hr/>
<c:forEach var="i"  items="<%=vector%>" begin="0" step="2" end="1">
 	:: vector 저장정보 추출 : ${i}<br/>
 </c:forEach><br/><hr/>

 
 <h3>4.key=value형식의 ㅡ메dml value 접근</h3> <br/>
 <%
 	java.util.HashMap hasMap = new java.util.HashMap();
 	hasMap.put("a","A");
 	hasMap.put("b","B");
 	hasMap.put("c","C");
 	hasMap.put("d","D");
 	%>
 
 <c:forEach var="i"  items="<%=hasMap%>">
 	:: HashMap에 저장정보 추출 : ${i.key} = ${i.value}<br/>
 </c:forEach><br/><hr/>
 
<h3> 5.EL /c:set, c:if를 동시 사용</h3><br/>
<c:set var="aaa" value="<%=hasMap%>" />
<c:forEach var="i" items="${aaa}">
 	<c:if test ="${i.key=='a'}">
 	::key a 의 value : ${i.value} <br/>
 	</c:if>
 	<c:if test ="${i.key=='a' || i.key == 'd'}">
 	::조건2개 if - key a 의 value : ${i.value}<br/>
 	</c:if>
 </c:forEach>
 