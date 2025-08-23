<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <title>공지 등록</title>
  <meta charset="UTF-8" />
  <style>
    body { font-family: system-ui, sans-serif; margin: 24px; }
    h2 { margin: 0 0 16px; }
    .wrap { max-width: 880px; }
    .form-row { display:flex; gap:12px; margin-bottom:12px; }
    .form-col { flex:1; display:flex; flex-direction:column; }
    label { font-weight:600; margin-bottom:6px; }
    input[type="text"], textarea {
      padding:10px 12px; border:1px solid #e5e7eb; border-radius:10px; font: inherit;
    }
    textarea { min-height: 220px; resize: vertical; }
    .actions { display:flex; gap:8px; margin-top:16px; }
    .btn { padding:10px 14px; border:1px solid #111827; color:#111827; border-radius:10px; text-decoration:none; font-weight:600; cursor:pointer; background:#fff; }
    .btn.primary { background:#111827; color:#fff; }
    .error { margin-top:10px; color:#b91c1c; font-weight:600; }
    .muted { color:#6b7280; }
  </style>
  <script>
    function validateAndSubmit(frm){
      // 필수값 체크 (공백 제거)
      function isEmpty(v){ return !v || v.replace(/\s+/g,'') === ''; }
      const authorId = frm.authorId.value.trim();
      const title    = frm.title.value.trim();
      const content  = frm.content.value.trim();

      if(isEmpty(authorId)){ alert("작성자ID는 필수입니다."); frm.authorId.focus(); return false; }
      if(isEmpty(title)){ alert("제목은 필수입니다."); frm.title.focus(); return false; }
      if(isEmpty(content)){ alert("내용은 필수입니다."); frm.content.focus(); return false; }

      // 제목 길이 수비: DB 100자 제한 고려
      if(title.length > 100){ alert("제목은 100자 이내로 입력해주세요."); frm.title.focus(); return false; }

      frm.submit();
    }
  </script>
</head>
<body>
<div class="wrap">
  <h2>공지 등록</h2>
  <div class="muted" style="margin-bottom:14px;">필수 항목을 입력한 뒤 등록을 눌러주세요.</div>

  <form name="addForm" method="post" action="/addNotice.do" accept-charset="UTF-8">
    <div class="form-row">
      <div class="form-col">
        <label for="authorId">작성자 ID</label>
        <input type="text" id="authorId" name="authorId"
               value="${fn:escapeXml(param.authorId)}"
               placeholder="예) user01" required />
      </div>
      <div class="form-col">
        <label for="title">제목</label>
        <input type="text" id="title" name="title"
               value="${fn:escapeXml(param.title)}"
               maxlength="100" placeholder="공지 제목을 입력하세요" required />
      </div>
    </div>

    <div class="form-row">
      <div class="form-col">
        <label for="content">내용</label>
        <textarea id="content" name="content" placeholder="공지 내용을 입력하세요" required>${fn:escapeXml(param.content)}</textarea>
      </div>
    </div>

    <c:if test="${not empty error}">
      <div class="error">${fn:escapeXml(error)}</div>
    </c:if>

    <div class="actions">
      <button type="button" class="btn primary" onclick="validateAndSubmit(document.addForm)">등록</button>
      <a class="btn" href="/listNotice.do">취소</a>
    </div>
  </form>
</div>
</body>
</html>
