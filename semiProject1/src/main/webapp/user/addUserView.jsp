<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>회원가입</title></head>
<body>
<h2>회원가입</h2>
<!-- (선택) 서버 검증 메시지 텍스트로도 보여주고 싶을 때 -->
<c:if test="${not empty error}">
 <p style="color:#d00; font-weight:600;">${error}</p>
</c:if>
<form action="<c:url value='/addUser.do'/>" method="post" id="addForm">
 아이디*
 <input name="userid" id="userid" maxlength="20" required
        value="${prefill_userid != null ? prefill_userid : ''}"/>
 <br/>
 비밀번호*
 <input type="password" name="password" required/><br/>
 이름*
 <input name="name" required
        value="${prefill_name != null ? prefill_name : ''}"/><br/>
 생년월일*
 <input type="date" name="birthdate" required
        value="${prefill_birthdate != null ? prefill_birthdate : ''}"/><br/>
 연락처*
 <input name="phone" required
        value="${prefill_phone != null ? prefill_phone : ''}"/><br/>
 지역*
 <input name="region" required placeholder="서울시 구로구"
        value="${prefill_region != null ? prefill_region : ''}"/><br/>
 성별*
 <select name="gender" required>
   <option value="M" <c:if test="${prefill_gender == 'M'}">selected</c:if>>남</option>
   <option value="F" <c:if test="${prefill_gender == 'F'}">selected</c:if>>여</option>
 </select><br/>
 카테고리(선택)
 <input name="category" placeholder="병원동행 등"
        value="${prefill_category != null ? prefill_category : ''}"/><br/><br/>
 <button type="submit">가입하기</button>
</form>
<!-- 서버에서 dupUserId가 오면 alert만 띄우고 아이디에 포커스 -->
<c:if test="${not empty dupUserId}">
 <script>
   alert('이미 사용 중인 아이디입니다: ${dupUserId}');
   setTimeout(function(){ document.getElementById('userid').focus(); }, 0);
 </script>
</c:if>
</body>
</html>