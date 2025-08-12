<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>

문자 : <%="홍길동"%><br>
숫자 : <%=1234%><br>
<br><br><br>
문자 : ${ "홍길동"}<br>
숫자 : ${1234}<br>
<br><br><br>

<h3> 1. 기본적 산술 , 논리, 관계 연산자 사용</h3>
숫자 + 숫자 : ${1+2} <br>
숫자 + 문자 : ${1+"2"}<br>


<h4> expression tag 사용 : 문자 * 숫자 : <%=Integer.parseInt("1")+2 %></h4>
10==10 : ${10 == 10} <br>
10 >= 1: ${10>=1}<br>
!true : ${!true} <br/><hr/><br/> 


<h3> 2. 조건 연산자 사용</h3>
조건 10 > 100? true : false = ${10 > 100 ? "10이 100보다 크다 true " : "10이 100보다 작다 false" }

<h3> 3.empty 연산자 사용 </h3><p/>
empty null : ${empty null }<br/>
empty " " : ${empty " " }<br/>
empty "" : ${empty "" }<br/> &nbsp;&nbsp;&nbsp; null String을 이해하자 String str = new String()



