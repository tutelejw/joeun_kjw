<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 공통 상단 영역: topbar 포함(JSPF 정적 include) --%>
<%@ include file="/common/top.jspf" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 등록</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>

  <style>
    /* 화면 중앙 정렬용 패널 */
    .panel {
      max-width: 920px;
      margin: 24px auto 40px;
      background: rgba(255,255,255,0.9);
      border-radius: 16px;
      padding: 24px 28px;
      box-shadow: 0 6px 22px rgba(0,0,0,0.08);
    }
    .panel h2 {
      margin: 0 0 14px;
      font-size: 1.25rem;
      font-weight: 700;
    }
    .panel .desc {
      color: #666;
      margin-bottom: 16px;
      line-height: 1.5;
    }
    .form-row { margin: 12px 0; }
    .form-row label {
      display: block;
      font-weight: 600;
      margin-bottom: 6px;
    }
    .form-row input[type="text"],
    .form-row textarea {
      width: 100%;
      border: 1px solid #ddd;
      border-radius: 10px;
      padding: 10px 12px;
      font-size: 0.95rem;
      box-sizing: border-box;
    }
    .actions { margin-top: 18px; display: flex; gap: 8px; }
    .btn {
      display: inline-block;
      padding: 10px 16px;
      border-radius: 10px;
      border: 0;
      background: #2a7f62; /* 기본 버튼(초록) */
      color: #fff; font-weight: 600; cursor: pointer;
      text-decoration: none;
    }
    .btn.secondary { background: #6c757d; }
    .error {
      margin: 10px 0 0;
      color: #c62828;
      font-size: 0.9rem;
    }
    .notice-box {
      border: 1px dashed #bbb;
      padding: 18px;
      border-radius: 12px;
      background: #fafafa;
      color: #333;
    }
  </style>

  <script>
    // 간단 유효성 검사(JS만 사용, 스크립틀릿 없음)
    function onSubmitAddNotice(e) {
      const f = e.target;
      const title   = f.title.value.trim();
      const content = f.content.value.trim();
      if (!title)  { alert("제목은 필수입니다.");  f.title.focus();   e.preventDefault(); return false; }
      if (!content){ alert("내용은 필수입니다.");  f.content.focus(); e.preventDefault(); return false; }
      return true;
    }
  </script>
</head>

<body>

<%-- 로그인/관리자 여부 계산(EL만 사용) --%>
<c:set var="isLogin" value="${not empty sessionScope.loginUser}" />
<c:set var="isAdmin" value="${isLogin and sessionScope.loginUser.userId eq 'admin'}" />

<div class="panel">
  <h2>공지 등록</h2>
  <p class="desc">공지 글은 서비스 전체에 노출됩니다. 정확하고 간결하게 작성해주세요.</p>

  <%-- admin만 등록 폼 노출 --%>
  <c:if test="${isAdmin}">
    <form method="post" action="<c:url value='/addNotice.do'/>" onsubmit="return onSubmitAddNotice(event)">
      <div class="form-row">
        <label for="title">제목</label>
        <input id="title" type="text" name="title" maxlength="100"
               value="${fn:escapeXml(param.title)}" />
      </div>

      <div class="form-row">
        <label for="content">내용</label>
        <textarea id="content" name="content" rows="10"
                  placeholder="공지 내용을 입력하세요.">${fn:escapeXml(param.content)}</textarea>
      </div>

      <div class="actions">
        <button class="btn" type="submit">등록</button>
        <a class="btn secondary" href="<c:url value='/listNotice.do'/>">목록</a>
      </div>

      <%-- 서버 유효성 실패/예외 메시지 표시 --%>
      <c:if test="${not empty requestScope.error}">
        <div class="error">${requestScope.error}</div>
      </c:if>
    </form>
  </c:if>

  <%-- 비관리자 분기: 안내만 노출 --%>
  <c:if test="${not isAdmin}">
    <div class="notice-box">
      <strong>권한 안내</strong><br/>
      공지 등록은 관리자만 가능합니다.<br/>
      <c:choose>
        <c:when test="${isLogin}">
          현재 계정: <strong>${sessionScope.loginUser.userId}</strong><br/>
        </c:when>
        <c:otherwise>
          현재 로그인되어 있지 않습니다.<br/>
        </c:otherwise>
      </c:choose>
      <div style="margin-top:10px;">
        <a class="btn secondary" href="<c:url value='/listNotice.do'/>">공지 목록으로</a>
      </div>
    </div>
  </c:if>
</div>

<%-- 공통 하단 영역: footer 포함 --%>
<jsp:include page="/common/footer.jspf" />

</body>
</html>
