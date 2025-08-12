<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.*" %>

<h3>4. 색인(. , [] ) 연산자 사용</h3><p/>

<%-- 아래내용은 Servlet Controller 가  화면구성 위해 ObjectScope 에 저장 한 것 으로 생각 --%>
<%
	// 1. page ObjectScope 저장
	pageContext.setAttribute("abc",new String("page ObjectScope 저장된 문자열"));
	
	// 2. request ObjectScope 저장
	request.setAttribute("abc",new String("request ObjectScope 저장된 문자열"));
	
	//3.1 session ObjectScope  (size() ==0인)  ArrayList 저장
	session.setAttribute("abc",new ArrayList());
	
	// 3.2 session ObjectScope 값을 갖는 ArrayList 저장
	ArrayList arrayList =new ArrayList();
	arrayList.add( new String("ArrayList  저장된 문자열") );
	session.setAttribute("def",arrayList);
	
	// 4. Application ObjectScope 값을 갖는 HashMap 저장
	HashMap hashMap = new HashMap();
	hashMap.put("zzz",new String("HashMap 저장된 문자열"));
	application.setAttribute("abc",hashMap);
%>

<!-- 
   	:: EL : 자체 연산자 . 와 [] 연사자를 제공
   	:: EL : ObjectScope 을 사용 할 수 있는 자체적으로 implicit Object 제공 
		page				==>pageScope,
		request			==>requestScope
		session			==> sessionScope
		application	==>applicationScop
-->

<h5> 1. page ObjectScope 접근</h5>
${pageScope.abc}  =${pageScope["abc"]}
  
<h5> 2.request ObjectScope 접근</h5>
${requestScope.abc}  =${requestScope["abc"]}

<h5> 3.session ObjectScope 접근 :  저장된 ArrayList empty 유무</h5>
${empty sessionScope.abc}  =${empty sessionScope["abc"]}

<h5> 4.1session ObjectScope 접근 :  저장된 ArrayList empty 유무</h5>
${empty sessionScope.def}  =${empty sessionScope["def"]}

<h5> 4.2session ObjectScope 접근 :  저장된 ArrayList empty 유무</h5>
${sessionScope.def[0]}  =${sessionScope["def"][0]}

<h5> 5.1 application ObjectScope 접근 :  저장된 HashList empty 유무</h5>
${empty aplicationScope.abc}  =${empty applicationScope["abc"]}

<h5> 5.2 application ObjectScope 접근 </h5>
${applicationScope.abc.zzz}  = ${applicationScope["abc"].zzz}

<h3> Object Scope 의 영역을 지정하지 않고 EL을 사용 abc 접근시</h3>
<h2> 어떤 ObjectScope 값이 추출 되는 지 확인하자</h2>
<h1> 어떤 ObjectScope 의 내용인가: ${abc}</h1>










