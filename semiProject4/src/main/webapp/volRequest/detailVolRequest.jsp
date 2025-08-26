<%-- /volRequest/detailVolRequest.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>봉사요청 상세</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
<%@ include file="/common/top.jspf"%>

<%-- 상세 객체에서 volunteerId 뽑아오기 (프로젝트에 맞게 한 줄만 선택 사용) --%>
<c:set var="volId" value="${volunteerId}" />
<c:if test="${empty volId}">
  <c:set var="volId" value="${not empty volunteer ? volunteer.volunteerId : null}" />
</c:if>

<%-- ========================[수정된 부분 시작]======================== --%>
<%-- JS 파일에 값을 전달하기 위해 main 태그에 data-* 속성 추가 --%>
<main style="padding:16px;" 
      data-volunteer-id="${volId}" 
      data-context-path="${pageContext.request.contextPath}">
<%-- ========================[수정된 부분 끝]========================== --%>
  <h2>봉사요청 상세</h2>

  <%-- 상세 본문(예시: 필드는 프로젝트 VO에 맞춰 사용) --%>
  <div style="margin-top:8px;">
    <div><strong>제목</strong> : ${fn:escapeXml(volunteer.title)}</div>
    <div><strong>작성자</strong> : ${fn:escapeXml(volunteer.authorId)}</div>
    <div><strong>연락처</strong> : ${fn:escapeXml(volunteer.phone)}</div>
    <div><strong>지역</strong> : ${fn:escapeXml(volunteer.region)}</div>
    <div><strong>카테고리</strong> : ${fn:escapeXml(volunteer.category)}</div>
    <div><strong>기간</strong> :
      <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm" />
      ~
      <fmt:formatDate value="${endTime}" pattern="yyyy-MM-dd HH:mm" />
    </div>
    
    <c:if test="${not empty volunteer.image}">
	  <img src="${pageContext.request.contextPath}/images/${volunteer.image}" style="max-width:600px">
	</c:if>
    
    <div style="margin-top:8px; white-space:pre-line;">
      ${fn:escapeXml(volunteer.content)}
    </div>
  </div>
  
  <!-- 상단 액션 영역 -->
<div style="display:flex; justify-content:flex-end; gap:8px; margin:8px 0 12px;">
  <!-- 작성자 본인: 수정 / 삭제 / (선택) 모집상태 토글 -->
  <c:if test="${sessionScope.user.userId == volunteer.authorId 
             and volunteer.status ne '봉사완료' 
             and volunteer.status ne '만료'}">

	  <!-- 수정 -->
	  <c:url var="editUrl" value="/updateVolRequestView.do">
	    <c:param name="volunteerId" value="${volId}"/>
	    <c:if test="${not empty param.region}"><c:param name="region" value="${param.region}"/></c:if>
	    <c:if test="${not empty param.category}"><c:param name="category" value="${param.category}"/></c:if>
	    <c:if test="${not empty param.page}"><c:param name="page" value="${param.page}"/></c:if>
	  </c:url>
	  <a class="btn" href="${editUrl}">수정</a>
	
	  <!-- 삭제 -->
	  <form method="post" action="<c:url value='/deleteVolRequest.do'/>" style="display:inline;"
	        onsubmit="return confirm('게시글을 삭제하시겠습니까?');">
	    <input type="hidden" name="volunteerId" value="${volId}"/>
	    <button class="btn" type="submit">삭제</button>
	  </form>
	
  </c:if>


  <!-- 작성자 외 사용자: 신청 버튼 -->
  <c:if test="${sessionScope.user.userId != volunteer.authorId}">
    <c:choose>
      <c:when test="${volunteer.status == '모집중'}">
        <!-- 바로 신청 POST (또는 addVolOfferView.do로 이동해도 됨) -->
        <form method="post" action="<c:url value='/processVolRequest.do'/>" style="display:inline;">
          <input type="hidden" name="volunteerId" value="${volId}"/>
          <button class="btn" type="submit">신청하기</button>
        </form>
      </c:when>
      <c:otherwise>
        <button class="btn" type="button" disabled>신청불가 (${volunteer.status})</button>
      </c:otherwise>
    </c:choose>
  </c:if>
</div>
  

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
    <c:url var="backUrl" value="/listVolRequest.do">
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

<%-- ========================[수정된 부분 시작]======================== --%>
<%-- 기존에 있던 <script>...</script> 블록 전체를 삭제하고, 아래의 한 줄로 대체합니다. --%>
<script src="<c:url value='/js/comment.js'/>"></script>
<%-- ========================[수정된 부분 끝]========================== --%>
</body>
</html>