<%-- /volOffer/detailVolOffer.jsp (div 기반으로 리디자인) --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>봉사제공 상세</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
<%@ include file="/common/top.jspf"%>

<%-- 상세 객체에서 volunteerId(=postId) 뽑아오기 --%>
<c:set var="volId" value="${volunteerId}" />
<c:if test="${empty volId}">
  <c:set var="volId" value="${volOffer.postId}" />
</c:if>

<main style="padding:16px;"
      data-volunteer-id="${volId}"
      data-context-path="${pageContext.request.contextPath}">

  <h2>봉사제공 상세</h2>

  <%-- 상세 본문: 테이블 → div 기반으로 변경 --%>
  <div style="margin-top:8px;">
    <div><strong>제목</strong> : ${fn:escapeXml(volOffer.title)}</div>
    <div><strong>작성자</strong> : ${fn:escapeXml(volOffer.authorId)}</div>
    <div><strong>연락처</strong> : ${fn:escapeXml(volOffer.phone)}</div>
    <div><strong>지역</strong> : ${fn:escapeXml(volOffer.region)}</div>
    <div><strong>카테고리</strong> : ${fn:escapeXml(volOffer.category)}</div>
    <div><strong>기간</strong> :
      <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm" />
      ~
      <fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm" />
    </div>
    <div style="margin-top:8px; white-space:pre-line;">
      ${fn:escapeXml(volOffer.content)}
    </div>
  </div>

  <%-- 작성자 본인에게만 수정/삭제 노출 --%>
  <c:if test="${sessionScope.user.userId == volOffer.authorId}">
    <div style="margin-top:12px; display:flex; gap:8px;">
      <!-- 수정 -->
      <c:url var="editUrl" value="/updateVolOfferView.do">
        <c:param name="postId" value="${volOffer.postId}"/>
        <c:if test="${not empty param.region}"><c:param name="region" value="${param.region}"/></c:if>
        <c:if test="${not empty param.category}"><c:param name="category" value="${param.category}"/></c:if>
        <c:if test="${not empty param.page}"><c:param name="page" value="${param.page}"/></c:if>
      </c:url>
      <a class="btn" href="${editUrl}">수정</a>

      <!-- 삭제 -->
      <form id="deleteForm" method="post" action="<c:url value='/deleteVolOffer.do'/>" style="display:inline;"
            onsubmit="return confirm('게시글을 삭제하시겠습니까?');">
        <input type="hidden" name="postId" value="${volOffer.postId}"/>
        <button class="btn" type="submit">삭제</button>
      </form>
    </div>
  </c:if>

  <hr style="margin:16px 0;"/>

  <%-- 댓글 섹션 (세그먼트 include) --%>
  <a id="comment-area"></a>
  <h3>댓글</h3>

  <%-- 댓글 작성 폼 (ID 추가) --%>
  <form id="comment-add-form" method="post" action="<c:url value='/addComment.do'/>"
        style="padding:10px; background:var(--surface); border:1px solid var(--line); border-radius:10px; margin-bottom:12px;">
    <input type="hidden" name="volunteerId" value="${volId}"/>

    <textarea name="content" rows="3" maxlength="4000" required
              placeholder="댓글을 입력하세요"
              style="width:100%; box-sizing:border-box;"></textarea>

    <div style="margin-top:8px; display:flex; gap:8px; justify-content:flex-end;">
      <button type="submit" class="btn">등록</button>
    </div>
  </form>

  <%-- 댓글 목록을 동적으로 교체하기 위해 div로 감싸고 ID 부여 --%>
  <div id="comment-list-container">
    <c:import url="/listComment.do">
      <c:param name="volunteerId" value="${volId}" />
      <c:param name="page" value="${param.commentPage != null ? param.commentPage : 1}" />
      <c:param name="pageSize"
               value="${initParam['comment.pageSize'] != null ? initParam['comment.pageSize'] : initParam['pageSize']}" />
    </c:import>
  </div>

  <%-- 뒤로가기(선택): 목록 검색조건 유지 --%>
  <div style="margin-top:16px;">
    <c:url var="backUrl" value="/listVolOffer.do">
      <c:if test="${not empty param.region}">
        <c:param name="region" value="${param.region}" />
      </c:if>
      <c:if test="${not empty param.category}">
        <c:param name="category" value="${param.category}" />
      </c:if>
      <c:if test="${not empty param.page}">
        <c:param name="page" value="${param.page}" />
      </c:if>
      <c:param name="pageSize" value="${initParam['pageSize']}" />
    </c:url>
    <a class="btn" href="${backUrl}">목록으로</a>
  </div>
</main>

<%@ include file="/common/footer.jspf"%>

<%-- 댓글 비동기 스크립트 --%>
<script src="<c:url value='/js/comment.js'/>"></script>
</body>
</html>
