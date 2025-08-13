<%@ page contentType="text/html;charset=UTF-8" %>

<!-- JSTL 사용 :  taglib 지시자 설정-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>ㅇ taglib의 c:set, c:remove,c:out을 사용</h3>

<!-- page ObjectScope 에 num1 이름의  값 "100" 저장 -->
<c:set var="num1" value="100" scope="page"/>

<!-- page ObjectScope 에 num2 이름의  값 "" 저장 -->
<c:set var="num2" />
<!-- Default ObjectScope : page / Default Value : nullString-->

<% 
	// page ObjectScope 에 num2 Value 확인
	String abc = (String)pageContext.getAttribute("num2");
	System.out.println("1 : _"+abc+"_");
%>


1. num1은 empty : ${empty pageScope.num1}  num1= ${ pageScope.num1} <br/>
2. num2은 empty : ${empty num2}  num2 = _${num2}_ <br/>


<hr/>
3. num1+num2 : ${num1+pageScope.num2}<br/>
<!-- c:out 사용 ? /  EL 사용 : 개발자의 선택-->
4. c:out 을 사용한 num1+num2 : <c:out value="${num1+num2}" /> <br/>
4. EL     을 사용한 num1+num2 :  ${num1+num2} <br/>
<hr/>


<!-- page Object Scope 에 num1 이름의  값 "num1+100" 다시저장: 덥어쓰기? -->
<c:set var="num1" value="${num1+100}" />
5. num1 : ${num1} <br/><hr/>


<!-- page Object Scope 에 num1  지우기-->
<c:remove var="num1" scope="page" />
 
<!--  page Object Scope 에 num1  지우기 : 지웠으면 NULL ????
       화면기술은 Error 에관대, null 에 관대 : 아래 콘솔은 어떻게 나오는지 확인 -->
6. num1은 empty : ${empty num1}  num1 = _${num1}_ <br/>
7. num2은 empty : ${empty num2}  num2 = _${pageScope.num2}_

<% 
	// page ObjectScope 에서  num1  remove 확인
	String def = (String)pageContext.getAttribute("num1");
	System.out.println("2:_"+def+"_");
%>
