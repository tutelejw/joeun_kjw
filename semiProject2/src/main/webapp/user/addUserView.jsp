<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>회원가입</title>
  <!-- 공통 CSS 링크 (경로는 프로젝트에 맞게) -->
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">

</head>
<body>

<div class="form-wrap">
  <h2 class="form-title">회원가입</h2>
  <p class="form-sub">필수 정보를 입력해 주세요.</p>

  <!-- 서버 측 메시지가 있을 때 상단 배너로 노출 -->
  <c:if test="${not empty errorMsg}">
    <div class="form-alert">${errorMsg}</div>
  </c:if>

  <form action="<c:url value='/addUser.do'/>" method="post" id="addForm" class="form-grid">

    <div class="form-row">
      <label for="userid" class="form-label">아이디*</label>
      <input class="input" name="userid" id="userid" maxlength="20" required
             value="${prefill_userid != null ? prefill_userid : ''}">
    </div>

    <div class="form-row">
      <label for="password" class="form-label">비밀번호*</label>
      <input class="input" type="password" name="password" id="password" required>
    </div>

    <div class="form-grid cols-2">
      <div class="form-row">
        <label for="name" class="form-label">이름*</label>
        <input class="input" name="name" id="name" required
               value="${prefill_name != null ? prefill_name : ''}">
      </div>
      <div class="form-row">
        <label for="birthdate" class="form-label">생년월일*</label>
        <input class="input" type="date" name="birthdate" id="birthdate" required
               value="${prefill_birthdate != null ? prefill_birthdate : ''}">
      </div>
    </div>

    <div class="form-grid cols-2">
      <div class="form-row">
        <label for="phone" class="form-label">연락처*</label>
        <input class="input" name="phone" id="phone" required
               value="${prefill_phone != null ? prefill_phone : ''}">
      </div>
      <div class="form-row">
        <label for="region" class="form-label">지역*</label>
        <input class="input" name="region" id="region" required placeholder="서울시 구로구"
               value="${prefill_region != null ? prefill_region : ''}">
      </div>
    </div>

    <div class="form-grid cols-2">
      <div class="form-row">
        <label for="gender" class="form-label">성별*</label>
        <select class="input" name="gender" id="gender" required>
          <option value="M" <c:if test="${prefill_gender == 'M'}">selected</c:if>>남</option>
          <option value="F" <c:if test="${prefill_gender == 'F'}">selected</c:if>>여</option>
        </select>
      </div>
      <div class="form-row">
        <label for="category" class="form-label">카테고리(선택)</label>
        <input class="input" name="category" id="category" placeholder="병원동행 등"
               value="${prefill_category != null ? prefill_category : ''}">
      </div>
    </div>

    <div class="mt-24">
      <button type="submit" class="btn">가입하기</button>
    </div>

  </form>
</div>

</body>
</html>
