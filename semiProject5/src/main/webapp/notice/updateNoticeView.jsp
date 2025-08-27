<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ include file="/common/top.jspf" %>

<c:set var="n" value="${empty notice ? (empty vo ? (empty item ? null : item) : vo) : notice}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 수정</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>
  <style>
    .panel{background: rgba(255,255,255,0.55);border: 1px solid var(--line);border-radius: 16px;overflow: hidden;
      background-clip: padding-box; max-width: 900px;margin: 24px auto;box-shadow: 0 8px 24px rgba(0,0,0,.12);
      backdrop-filter: blur(12px) saturate(120%);-webkit-backdrop-filter: blur(12px) saturate(120%);}
    .panel-header{display:flex;justify-content:space-between;align-items:center;padding:14px 16px;border-bottom:1px solid var(--line)}
    .panel-title{font-size:18px;font-weight:700}
    .panel-body{padding:18px 16px}
    .panel-footer{display:flex;gap:10px;justify-content:flex-end;align-items:center;padding:14px 16px;border-top:1px solid var(--line)}
    .field{display:flex;flex-direction:column;gap:6px;margin-bottom:14px}
    .field label{font-weight:700}
    .field input[type=text], .field textarea{border:1px solid var(--line);border-radius:10px;padding:10px;width:100%}
    .field input[readonly]{background:#f7f7f7}
    textarea{min-height:200px;resize:vertical}
    .btn{display:inline-flex;align-items:center;justify-content:center;height:40px;padding:0 16px;border-radius:10px;border:1px solid var(--line);text-decoration:none}
    .btn.primary{background:#11a36c;color:#fff;border-color:#11a36c}
    .btn.secondary{background:#fff;color:#111}
  </style>
</head>
<body>

<main>
  <section class="panel">
    <div class="panel-header">
      <div class="panel-title">공지 수정</div>
      <a class="btn secondary" href="<c:url value='/listNotice.do'/>">목록</a>
    </div>

    <div class="panel-body">
      <c:choose>
        <c:when test="${empty n}">
          <div>수정할 공지를 찾을 수 없습니다.</div>
        </c:when>
        <c:otherwise>

          <form action="<c:url value='/updateNotice.do'/>" method="post" accept-charset="UTF-8">
            <input type="hidden" name="noticeId" value="${n.noticeId}" />

            <div class="field">
              <label for="title">제목</label>
              <input id="title" name="title" type="text" maxlength="100"
                     value="${fn:escapeXml(n.title)}" required />
            </div>

            <div class="field">
              <label for="content">내용</label>
              <textarea id="content" name="content" maxlength="4000" required>${fn:escapeXml(n.content)}</textarea>
            </div>

            <div class="panel-footer">
              <a class="btn secondary"
                 href="<c:url value='/detailNotice.do'><c:param name='noticeId' value='${n.noticeId}'/></c:url>">취소</a>
              <button type="submit" class="btn primary">수정완료</button>
            </div>
          </form>

        </c:otherwise>
      </c:choose>
    </div>
  </section>
</main>

<%@ include file="/common/footer.jspf"%>

</body>
</html>
