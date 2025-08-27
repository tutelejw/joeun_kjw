<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 공통 상단 영역: topbar 포함 --%>
<%@ include file="/common/top.jspf" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 상세</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>

  <style>
    .panel {
      max-width: 1100px; /* 기본 패널 폭 */
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
    .meta {
      color: #666;
      font-size: 0.92rem;
      margin-bottom: 12px;
    }
    .field {
      margin: 14px 0;
    }
    .field .label {
      display: block;
      font-weight: 700;
      margin-bottom: 6px;
    }
    .content-box {
      border: 1px solid #ddd;
      border-radius: 10px;
      padding: 12px 14px;
      background: #fff;
      min-height: 150px;
      white-space: pre-wrap;
    }
    .actions {
      margin-top: 18px;
      display: flex;
      gap: 8px;
      flex-wrap: wrap;
    }
    .btn {
      display: inline-block;
      padding: 10px 16px;
      border-radius: 10px;
      border: 0;
      background: #2a7f62;
      color: #fff;
      font-weight: 600;
      cursor: pointer;
      text-decoration: none;
    }
    .btn.secondary {
      background: #6c757d;
    }
    .btn.danger {
      background: #b3261e;
    }
    .error {
      margin: 10px 0 0;
      color: #c62828;
      font-size: 0.9rem;
    }
  </style>
</head>
<body>

<main>
  <%-- 로그인/관리자 여부 --%>
  <c:set var="isLogin" value="${not empty sessionScope.loginUser}" />
  <c:set var="isAdmin" value="${isLogin and sessionScope.loginUser.userId eq 'admin'}" />

  <%-- 이제 request 대신 session에서 직접 notice 객체를 가져옴 --%>
  <c:set var="notice" value="${sessionScope.notice}" />

  <%-- 작성일자 YYYYMMDD 구성 --%>
  <c:set var="createdAtStr"
         value="${empty notice.createdAt
                ? ''
                : fn:replace(fn:substring(notice.createdAt,0,10), '-', '')}" />

  <div class="panel" style="max-width:1100px;">
    <h2>공지 상세</h2>

    <div class="field">
      <span class="label">제목</span>
      <div class="content-box">${fn:escapeXml(notice.title)}</div>
    </div>

    <div class="field">
      <span class="label">내용</span>
      <div class="content-box">${fn:escapeXml(notice.content)}</div>
    </div>

    <div class="actions">
      <a class="btn secondary" href="<c:url value='/listNotice.do'/>">목록</a>

      <%-- 관리자 전용: 수정/삭제 버튼 노출 --%>
      <c:if test="${isAdmin}">
        <a class="btn"
           href="<c:url value='/updateNoticeView.do'>
                     <c:param name='noticeId' value='${notice.noticeId}'/>
                   </c:url>">수정</a>

        <form id="deleteForm" method="post" action="<c:url value='/deleteNotice.do'/>" style="display:inline;">
          <input type="hidden" name="noticeId" value="${notice.noticeId}">
          <button type="submit" class="btn danger" onclick="return confirm('해당 공지를 삭제하시겠습니까?');">삭제</button>
        </form>
      </c:if>
    </div>

    <c:if test="${not empty requestScope.error}">
      <div class="error">${requestScope.error}</div>
    </c:if>
  </div>
</main>

<%-- 공통 하단 영역: footer 포함 --%>
<%@ include file="/common/footer.jspf"%>

</body>
</html>