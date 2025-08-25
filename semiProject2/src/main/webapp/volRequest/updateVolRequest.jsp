<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>봉사요청 수정</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
<%@ include file="/common/top.jspf"%>

<div class="container-main">
  <div class="hero">
    <div class="title">봉사요청 수정</div>

    <form method="post" action="<c:url value='/updateVolRequest.do'/>" style="display:grid; grid-template-columns: 1fr 1fr; gap:18px;">
      <input type="hidden" name="volunteerId" value="${item.postId}"/>

      <div style="background:var(--surface); border:1px solid var(--line); border-radius:12px; padding:16px;">
        <div style="margin-bottom:10px;">
          <label>제목</label><br/>
          <input type="text" name="title" value="${item.title}" required style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
        </div>

        <div style="display:grid; grid-template-columns:1fr 1fr; gap:10px;">
          <div>
            <label>시작일시</label><br/>
            <input type="datetime-local" name="start"
                   value="${item.startTime}" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
          </div>
          <div>
            <label>종료일시</label><br/>
            <input type="datetime-local" name="end"
                   value="${item.endTime}" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
          </div>
        </div>

        <div style="margin-top:10px;">
          <label>연락처</label><br/>
          <input type="text" name="phone" value="${item.phone}" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
        </div>

        <div style="margin-top:10px;">
          <label>지역</label><br/>
          <input type="text" name="region" value="${item.region}" required style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
        </div>

        <div style="margin-top:10px;">
          <label>카테고리</label><br/>
          <input type="text" name="category" value="${item.category}" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">
        </div>
      </div>

      <div style="background:var(--surface); border:1px solid var(--line); border-radius:12px; padding:16px;">
        <div style="color:var(--subtle-ink); margin-bottom:8px;">현재 내용</div>
        <div style="white-space:pre-line; min-height:220px;">${item.content}</div>
      </div>

      <div style="grid-column: 1 / span 2; margin-top:8px;">
        <label>봉사요청 상세내용</label><br/>
        <textarea name="content" rows="8" style="width:100%; padding:10px; border:1px solid var(--line); border-radius:10px;">${item.content}</textarea>
      </div>

      <div style="grid-column: 1 / span 2; display:flex; gap:8px; justify-content:flex-end; margin-top:8px;">
        <a href="<c:url value='/detailVolRequest.do'>
                    <c:param name='volunteerId' value='${item.postId}'/>
                 </c:url>" class="btn">돌아가기</a>
        <button type="submit" class="btn">적용하기</button>
      </div>
    </form>
  </div>
</div>

<jsp:include page="/common/footer.jspf"/>
</body>
</html>
