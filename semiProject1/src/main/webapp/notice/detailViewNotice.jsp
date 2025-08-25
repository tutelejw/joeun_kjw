<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 모델 바인딩(여러 키 대응: notice/vo/item) --%>
<c:set var="model" value="${empty notice ? (empty vo ? (empty item ? null : item) : vo) : notice}" />

<%-- ========= 권한 체크 (세션) ========= --%>
<%-- 모든 유저에게 무조건 true로 설정! (임시) --%>
<c:set var="isAdmin" value="${true}" />

<%-- 삭제 후 돌아갈 리스트 URL(페이지/검색 유지) --%>
<c:url value="/listNotice.do" var="redirectUrl">
  <c:if test="${not empty param.currentPage}">
    <c:param name="currentPage" value="${param.currentPage}"/>
  </c:if>
  <c:if test="${not empty param.pageSize}">
    <c:param name="pageSize" value="${param.pageSize}"/>
  </c:if>
  <c:if test="${not empty param.q}">
    <c:param name="q" value="${param.q}"/>
  </c:if>
</c:url>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 상세보기</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>

  <style>
    /* 유리 패널 */
    .panel{
      background: rgba(255,255,255,0.55);
      border: 1px solid var(--line);
      border-radius: 16px;
      overflow: hidden;
      background-clip: padding-box;
      max-width: 1100px;
      margin: 24px auto;
      box-shadow: 0 8px 24px rgba(0,0,0,.12);
      backdrop-filter: blur(12px) saturate(120%);
      -webkit-backdrop-filter: blur(12px) saturate(120%);
    }
    .panel-header{display:flex;justify-content:space-between;align-items:center;padding:12px 14px;border-bottom:1px solid var(--line)}
    .panel-title{font-size:18px;font-weight:700}
    .panel-body{padding:16px 18px}
    .panel-footer{display:flex;justify-content:space-between;align-items:center;padding:12px 14px;border-top:1px solid var(--line)}
    .btn{display:inline-block;border:1px solid #222;padding:8px 12px;text-decoration:none;color:#111;background:#fff;border-radius:4px;font-weight:600;cursor:pointer}
    .btn.primary{background:#111;color:#fff}

    /* 상세: 하나의 불투명 네모 박스 */
    .detail-card{
      background: var(--surface);
      border: 1px solid var(--line);
      border-radius: 14px;
      padding: 18px;
      box-shadow: 0 4px 14px rgba(0,0,0,.06);
    }
    .meta{display:flex; gap:12px; flex-wrap:wrap; margin-bottom:14px; color:var(--subtle-ink); font-size:14px}
    .meta .chip{background:#f5f6f8;border:1px solid var(--line); padding:6px 10px; border-radius:10px}
    .title-view{font-size:20px; font-weight:700; margin:6px 0 12px}
    .content-view{white-space:pre-wrap; line-height:1.6; border-top:1px dashed var(--line); padding-top:12px; color:var(--ink)}

    @supports not ((backdrop-filter: blur(1px)) or (-webkit-backdrop-filter: blur(1px))){
      .panel{ background: rgba(255,255,255,0.9); }
    }
  </style>

  <script>
    function confirmDelete(noticeId){
      if(!noticeId){ alert("잘못된 요청입니다."); return; }
      if(confirm("정말로 이 공지를 삭제할까요? 삭제 후 되돌릴 수 없습니다.")){
        var f = document.getElementById("deleteForm");
        f.noticeId.value = noticeId;
        f.submit();
      }
    }
  </script>
</head>
<body>

<div class="topbar">
  <div class="container">
    <div class="brand"><a href="<c:url value='/'/>">온세상</a></div>
    <div class="nav">
      <a href="#"><span>마음부탁</span></a>
      <a href="#"><span>손길나눔</span></a>
      <a href="<c:url value='/listNotice.do'/>" class="active"><span>햇살소식</span></a>
      <div class="dropdown">
        <button type="button">마이페이지 ▾</button>
        <div class="dropdown-menu">
          <a href="#">내정보보기</a>
          <a href="#">비밀번호변경</a>
          <a href="#">내봉사이력</a>
          <a href="#">내요청이력</a>
        </div>
      </div>
      <a href="#"><span>로그인</span></a>
    </div>
  </div>
</div>

<main class="container-main">
  <section class="panel">
    <div class="panel-header">
      <div class="panel-title">공지 상세보기</div>
      <div>
        <a class="btn" href="${redirectUrl}">목록</a>
      </div>
    </div>

    <div class="panel-body">
      <c:choose>
        <c:when test="${not empty model}">
          <div class="detail-card">
            <div class="meta">
              <span class="chip">공지ID: <c:out value="${model.noticeId}"/></span>
              <span class="chip">작성자: <c:out value="${empty model.authorId ? 'admin' : model.authorId}"/></span>
              <span class="chip">작성일: <c:out value="${model.createdAt}"/></span>
            </div>

            <div class="title-view"><c:out value="${model.title}"/></div>
            <div class="content-view"><c:out value="${model.content}"/></div>
          </div>
        </c:when>
        <c:otherwise>
          <div class="detail-card">상세 정보를 불러올 수 없습니다.</div>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="panel-footer">
      <span></span>
      <div style="display:flex; gap:8px;">
        <a class="btn" href="${redirectUrl}">취소</a>

        <%-- 모든 유저에게 수정/삭제 버튼을 보이도록 c:if를 삭제 --%>
        <a class="btn"
           href="<c:url value='/updateNoticeView.do'>
                    <c:param name='noticeId' value='${model.noticeId}'/>
                    <c:if test='${not empty param.currentPage}'><c:param name='currentPage' value='${param.currentPage}'/></c:if>
                    <c:if test='${not empty param.pageSize}'><c:param name='pageSize' value='${param.pageSize}'/></c:if>
                    <c:if test='${not empty param.q}'><c:param name='q' value='${param.q}'/></c:if>
                 </c:url>">수정하기</a>

        <form id="deleteForm" method="post" action="<c:url value='/deleteNotice.do'/>" style="display:inline;">
          <input type="hidden" name="noticeId" value=""/>
          <input type="hidden" name="redirect" value="${redirectUrl}"/>
          <button type="button" class="btn primary"
                  onclick="confirmDelete('${model.noticeId}')">삭제</button>
        </form>
      </div>
    </div>
  </section>
</main>

<footer class="site-footer">© 온세상 너무조앙</footer>

</body>
</html>