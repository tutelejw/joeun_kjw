<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>로그인</title>
  <c:if test="${not empty loginError}">
    <script>
      alert('${fn:escapeXml(loginError)}');
      // optional: focus the ID field after the alert
      window.addEventListener('DOMContentLoaded', function(){ 
        var el = document.querySelector('input[name="userId"]'); 
        if(el) el.focus(); 
      });
    </script>
  </c:if>
</head>
<body>
<h2>로그인</h2>

<form action="<c:url value='/login.do'/>" method="post">
  아이디
  <input name="userId" maxlength="10" required pattern="[A-Za-z0-9]{1,10}"
         value="${not empty userId ? userId : param.userId}"/><br/>
  비밀번호
  <input type="password" name="password" required/><br/><br/>

  <button type="submit">로그인</button>
  <a href="<c:url value='/addUserView.do'/>">회원가입</a>
</form>
</body>
</html>
