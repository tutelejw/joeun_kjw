<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>봉사요청 등록</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
<%@ include file="/common/top.jspf"%>

<div class="container-main">
  <div class="hero">
    <div class="title">봉사요청 등록</div>

    <form method="post" action="<c:url value='/addVolRequest.do'/>" enctype="multipart/form-data" style="display:grid; grid-template-columns: 1fr 1fr; gap:18px;">
      <div style="background:var(--surface); border:1px solid var(--line); border-radius:12px; padding:16px;">
        <div style="margin-bottom:10px;">
          <label>제목</label><br/>
          <input type="text" name="title" required style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
          <div class="error-message"></div>
        </div>

        <div style="display:grid; grid-template-columns:1fr 1fr; gap:10px;">
          <div>
            <label>시작일시</label><br/>
            <input type="datetime-local" name="start" required style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
            <div class="error-message"></div>
          </div>
          <div>
            <label>종료일시</label><br/>
            <input type="datetime-local" name="end" required style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
            <div class="error-message"></div>
          </div>
        </div>

        <div style="margin-top:10px;">
          <label>연락처</label><br/>
          <input type="text" name="phone" placeholder="010-0000-0000" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
          <div class="error-message"></div>
        </div>

        <div style="margin-top:10px;">
          <label>지역</label><br/>
          <input type="text" name="region" value="${user.region}" required placeholder="예: 서울시 구로구" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
          <div class="error-message"></div>
        </div>

        <div style="margin-top:10px;">
          <label>카테고리</label><br/>
          <input type="text" name="category" placeholder="예: 병원동행" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
          <div class="error-message"></div>
        </div>
        
        <div class="form-row" style="margin-top:10px;">
	      <label>이미지</label>
	      <input type="file" name="image" accept="image/*">
	    </div>
      </div>
      
      <div style="background:var(--surface); border:1px solid var(--line); border-radius:12px; padding:16px;">
        <div style="color:var(--subtle-ink); margin-bottom:8px;">미리보기</div>
        <div id="preview" style="white-space:pre-line; min-height:220px;"></div>
      </div>

      <div style="grid-column: 1 / span 2; margin-top:8px;">
        <label>봉사요청 상세내용</label><br/>
        <textarea name="content" rows="8" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;"></textarea>
        <div class="error-message"></div>
      </div>

      <div style="grid-column: 1 / span 2; display:flex; gap:8px; justify-content:flex-end; margin-top:8px;">
        <a href="<c:url value='/listVolRequest.do'/>" class="btn">취소</a>
        <button type="submit" class="btn">등록하기</button>
      </div>
    </form>
  </div>
</div>

<%@ include file="/common/footer.jspf"%>

<script src="<c:url value='/js/form-validation.js'/>" defer></script>

</body>
</html>