<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8"/>
  <title>봉사요청 상세</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
<%@ include file="/common/top.jspf"%>

<div class="container-main">
  <div class="hero">
    <div class="title">봉사요청 상세</div>

    <div style="display:grid; grid-template-columns: 1fr 1fr; gap:18px;">
      <!-- 왼쪽: 라벨 목록 -->
      <div style="background:var(--surface); border:1px solid var(--line); border-radius:12px; padding:16px;">
        <div>제목</div>
        <div>작성자</div>
        <div>시작일시</div>
        <div>종료일시</div>
        <div>연락처</div>
        <div>지역</div>
        <div>카테고리</div>
        <div>상태</div>
        <div>작성일시</div>
        <div>내용</div>
      </div>

      <!-- 오른쪽: 값 -->
      <div style="background:var(--surface); border:1px solid var(--line); border-radius:12px; padding:16px;">
        <div><strong>${item.title}</strong></div>
        <div>${authorName}</div>
        <div>${item.startTime}</div>
        <div>${item.endTime}</div>
        <div>${item.phone}</div>
        <div>${item.region}</div>
        <div>${item.category}</div>
        <div>${item.status}</div>
        <div>${item.createdAt}</div>
        <div style="white-space:pre-line; margin-top:8px;">${item.content}</div>
      </div>
    </div>

    <!-- 버튼들 -->
    <div style="margin-top:12px; display:flex; gap:8px;">
      <a class="btn" href="<c:url value='/listVolRequest.do'>
          <c:param name='region' value='${param.region}'/>
          <c:param name='category' value='${param.category}'/>
          <c:param name='page' value='${param.page}'/>
          <c:param name='pageSize' value='${param.pageSize}'/>
        </c:url>">돌아가기</a>

      <!-- 작성자 본인일 때만 수정/삭제 노출(세션 userId 비교가 뷰에도 있다면) -->
      <c:if test="${sessionScope.userId == item.authorId}">
        <a class="btn" href="<c:url value='/updateVolRequestView.do'>
            <c:param name='volunteerId' value='${item.postId}'/>
            <c:param name='region' value='${param.region}'/>
            <c:param name='category' value='${param.category}'/>
            <c:param name='page' value='${param.page}'/>
            <c:param name='pageSize' value='${param.pageSize}'/>
          </c:url>">수정하기</a>

        <form method="post" action="<c:url value='/deleteVolRequest.do'/>" style="display:inline;">
          <input type="hidden" name="volunteerId" value="${item.postId}"/>
          <input type="hidden" name="region" value="${param.region}"/>
          <input type="hidden" name="category" value="${param.category}"/>
          <input type="hidden" name="page" value="${param.page}"/>
          <input type="hidden" name="pageSize" value="${param.pageSize}"/>
          <button type="submit" class="btn" onclick="return confirm('삭제하시겠습니까?')">삭제</button>
        </form>
      </c:if>

      <!-- 다른 사용자는 '신청하기'(상태 처리) -->
      <c:if test="${sessionScope.userId != item.authorId}">
        <form method="post" action="<c:url value='/processVolRequest.do'/>" style="display:inline;">
          <input type="hidden" name="volunteerId" value="${item.postId}"/>
          <input type="hidden" name="region" value="${param.region}"/>
          <input type="hidden" name="category" value="${param.category}"/>
          <input type="hidden" name="page" value="${param.page}"/>
          <input type="hidden" name="pageSize" value="${param.pageSize}"/>
          <button type="submit" class="btn">신청하기</button>
        </form>
      </c:if>
    </div>

    <!-- 댓글 영역 -->
    <div style="margin-top:18px;">
      <h4 style="margin:6px 0;">댓글</h4>

      <!-- 댓글 입력 -->
      <form method="post" action="<c:url value='/addComment.do'/>" style="display:flex; gap:8px; margin-bottom:10px;">
        <input type="hidden" name="volunteerId" value="${item.postId}"/>
        <input type="hidden" name="region" value="${param.region}"/>
        <input type="hidden" name="category" value="${param.category}"/>
        <input type="hidden" name="page" value="${param.page}"/>
        <input type="hidden" name="pageSize" value="${param.pageSize}"/>
        <input type="text" name="content" placeholder="댓글을 입력하세요" style="flex:1; padding:10px; border:1px solid var(--line); border-radius:10px;">
        <button type="submit" class="btn">등록</button>
      </form>

      <!-- 댓글 목록 (controller에서 request.setAttribute("comments", ...)로 넘길 때 표시됨) -->
      <c:if test="${not empty comments}">
        <ul style="list-style:none; padding:0; margin:0;">
          <c:forEach var="cmt" items="${comments}">
            <li style="padding:10px; border:1px solid var(--line); border-radius:10px; margin-bottom:8px; background:var(--surface);">
              <div style="display:flex; justify-content:space-between; align-items:center;">
                <div>
                  <div style="font-weight:600;">${cmt.authorId}</div>
                  <div style="color:var(--subtle-ink); font-size:12px;">${cmt.createdAt}</div>
                </div>
                <div>
                  <c:if test="${sessionScope.userId == cmt.authorId}">
                    <form method="post" action="<c:url value='/deleteComment.do'/>" style="display:inline;">
                      <input type="hidden" name="commentId" value="${cmt.commentId}"/>
                      <input type="hidden" name="volunteerId" value="${item.postId}"/>
                      <button class="btn" type="submit" onclick="return confirm('댓글을 삭제하시겠습니까?')">삭제</button>
                    </form>
                    <!-- 수정은 간단 폼 토글로 구현해도 되고, 별도 페이지 없이 처리 -->
                  </c:if>
                </div>
              </div>
              <div style="margin-top:6px; white-space:pre-line;">${cmt.content}</div>
            </li>
          </c:forEach>
        </ul>
      </c:if>
    </div>

  </div>
</div>

<jsp:include page="/common/footer.jspf"/>
</body>
</html>
