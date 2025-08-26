<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 공통 상단: 탑바 include --%>
<%@ include file="/common/top.jspf" %>

<%-- ===== 변수 초기화 및 바인딩 (컨트롤러에서 전달된 값 안전화) ===== --%>
<c:set var="totalCount"    value="${empty totalCount   ? 0  : totalCount}" />
<c:set var="currentPage"   value="${empty currentPage  ? 1  : currentPage}" />
<c:set var="pageSize"      value="${empty pageSize     ? 10 : pageSize}" />
<c:set var="totalPage"     value="${empty totalPage    ? 1  : totalPage}" />
<c:set var="startPage"     value="${empty startPage    ? 1  : startPage}" />
<c:set var="endPage"       value="${empty endPage      ? 1  : endPage}" />
<c:set var="items"         value="${not empty noticeList ? noticeList : (not empty list ? list : null)}" />
<c:set var="searchKeyword" value="${empty searchKeyword ? '' : searchKeyword}" />

<%-- ===== admin 노출을 위한 세션 사용자 식별 로직 (기존 로직 유지) ===== --%>
<c:set var="sessionUser"
       value="${empty sessionScope.user ? (empty sessionScope.loginUser ? (empty sessionScope.sessionUser ? null : sessionScope.sessionUser) : sessionScope.loginUser) : sessionScope.user}" />
<c:set var="sessionUserId"
       value="${empty sessionUser ? (empty sessionScope.userId ? '' : sessionScope.userId)
                 : (empty sessionUser.userId ? (empty sessionUser.id ? '' : sessionUser.id) : sessionUser.userId)}" />
<c:set var="isAdmin" value="${sessionUserId eq 'admin'}" />

<%-- 검색키(드롭다운) 현재값 보존용 --%>
<c:set var="sk" value="${param.searchKey}" />
<c:set var="q"  value="${fn:escapeXml(searchKeyword)}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 목록</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>
  <style>
    /* 화면 구성은 기존 유지, 표 스타일만 최소 보강 */
    table{width:100%;border-collapse:collapse}
    th,td{border:1px solid var(--line);padding:10px}
    th{background:#f8f8f8}
    .panel{background: rgba(255,255,255,0.55);border: 1px solid var(--line);border-radius: 16px;overflow: hidden;
  	  background-clip: padding-box; max-width: 1100px;margin: 24px auto;box-shadow: 0 8px 24px rgba(0,0,0,.12);
  	  backdrop-filter: blur(12px) saturate(120%);-webkit-backdrop-filter: blur(12px) saturate(120%);}
    .panel-header{display:flex;justify-content:space-between;align-items:center;padding:12px 14px;border-bottom:1px solid var(--line)}
    .panel-title{font-size:18px;font-weight:700}
    .panel-body{padding:6px 14px}
    .panel-footer{display:flex;justify-content:space-between;align-items:center;padding:12px 14px;border-top:1px solid var(--line)}
    .search-wrap{display:flex;gap:8px;align-items:center}
    .search-wrap input[type=text]{height:36px;padding:0 10px;border:1px solid var(--line);border-radius:8px;min-width:220px}
    .search-wrap select{height:36px;padding:0 8px;border:1px solid var(--line);border-radius:8px;min-width:100px}
    .btn{display:inline-flex;align-items:center;justify-content:center;height:36px;padding:0 14px;border-radius:8px;border:1px solid var(--line);text-decoration:none}
    .btn.primary{background:#11a36c;color:#fff;border-color:#11a36c}
    .btn.secondary{background:#fff;color:#111}
    .pagination{display:flex;gap:6px;align-items:center}
    .pagination a{padding:6px 10px;border-radius:8px;border:1px solid var(--line);text-decoration:none}
    .pagination .active{background:#11a36c;color:#fff;border-color:#11a36c}
    .empty{padding:20px 0;color:#777}
    .title-link{text-decoration:none;color:#111}
    .title-link:hover{text-decoration:underline}
  </style>
</head>
<body>

<main>
  <section class="panel">
    <%-- ===== 패널 헤더 (검색 폼) ===== --%>
    <div class="panel-header">
      <div class="panel-title">공지 목록</div>

      <%-- 검색 드롭다운 + 인풋 : 초기값 [선택], 선택 유지, q 유지 --%>
      <form class="search-wrap" action="<c:url value='/listNotice.do'/>" method="get">
        <select name="searchKey" aria-label="검색항목">
          <option value="" <c:if test="${empty sk}">selected</c:if>>선택</option>
          <option value="title" <c:if test="${sk eq 'title'}">selected</c:if>>제목</option>
        </select>
        <input type="text" name="q" value="${q}" placeholder="검색어 입력"/>
        <input type="hidden" name="pageSize" value="${pageSize}"/>
        <button type="submit" class="btn secondary">검색</button>
      </form>
    </div>
    
    <%-- ===== 패널 바디 (공지 목록 테이블) ===== --%>
    <div class="panel-body">
      <c:choose>
        <c:when test="${empty items}">
          <div class="empty">등록된 공지가 없습니다.</div>
        </c:when>
        <c:otherwise>
          <table>
            <colgroup>
              <col style="width:90px"/>
              <col/>
              <col style="width:120px"/></colgroup>
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성일</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="n" items="${items}" varStatus="st">
                <tr>
                  <td>${n.noticeId}</td>
                  <td>
                    <a class="title-link"
                       href="<c:url value='/detailNotice.do'><c:param name='noticeId' value='${n.noticeId}'/></c:url>">
                      ${fn:escapeXml(n.title)}
                    </a>
                  </td>
                  
                  <%-- createdAt 날짜 형식 변환 (yyyyMMdd) --%>
                  <c:set var="__ca" value="${n.createdAt}" />
                  <c:choose>
                    <c:when test="${empty __ca}">
                      <c:set var="__date8" value="" />
                    </c:when>
                    <c:otherwise>
                      <c:set var="__s0" value="${__ca}" />
                      <c:set var="__s1" value="${fn:replace(__s0, '-', '')}" />
                      <c:set var="__s2" value="${fn:replace(__s1, ':', '')}" />
                      <c:set var="__s3" value="${fn:replace(__s2, 'T', '')}" />
                      <c:choose>
                        <c:when test="${fn:length(__s3) >= 8}">
                          <c:set var="__date8" value="${fn:substring(__s3, 0, 8)}" />
                        </c:when>
                        <c:otherwise>
                          <c:set var="__date8" value="${__s3}" />
                        </c:otherwise>
                      </c:choose>
                    </c:otherwise>
                  </c:choose>
                  <td>${__date8}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:otherwise>
      </c:choose>
    </div>

    <%-- ===== 패널 푸터 (페이지네이션 및 버튼) ===== --%>
    <div class="panel-footer">
      <div class="pagination">
        <c:if test="${totalPage > 1}">
          <c:set var="q" value="${fn:escapeXml(searchKeyword)}"/>
          
          <c:if test="${startPage > 1}">
            <a href="<c:url value='/listNotice.do'>
                       <c:param name='page' value='${startPage-1}'/>
                       <c:param name='pageSize' value='${pageSize}'/>
                       <c:param name='q' value='${q}'/>
                       <c:param name='searchKey' value='title'/>
                     </c:url>">이전</a>
          </c:if>

          <c:forEach var="p" begin="${startPage}" end="${endPage}">
            <c:choose>
              <c:when test="${p == currentPage}">
                <span class="active">${p}</span>
              </c:when>
              <c:otherwise>
                <a href="<c:url value='/listNotice.do'>
                           <c:param name='page' value='${p}'/>
                           <c:param name='pageSize' value='${pageSize}'/>
                           <c:param name='q' value='${q}'/>
                           <c:param name='searchKey' value='title'/>
                         </c:url>">${p}</a>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          
          <c:if test="${endPage < totalPage}">
            <a href="<c:url value='/listNotice.do'>
                       <c:param name='page' value='${endPage+1}'/>
                       <c:param name='pageSize' value='${pageSize}'/>
                       <c:param name='q' value='${q}'/>
                       <c:param name='searchKey' value='title'/>
                     </c:url>">다음</a>
          </c:if>
        </c:if>
      </div>

      <%-- 등록 버튼: admin 세션일 때만 노출 --%>
      <c:if test="${isAdmin}">
        <a class="btn primary" href="<c:url value='/addNoticeView.do'/>">등록</a>
      </c:if>
      <%-- // isAdmin 분기 종료 --%>
    </div>
  </section>
</main>

<%-- 공통 하단: 풋터 include --%>
<%@ include file="/common/footer.jspf"%>

</body>
</html>