<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- ===== 기본값/바인딩 (컨트롤러에서 던진 값 안전화) ===== --%>
<c:set var="totalCount"    value="${empty totalCount   ? 0  : totalCount}" />
<c:set var="currentPage"   value="${empty currentPage  ? 1  : currentPage}" />
<c:set var="pageSize"      value="${empty pageSize     ? 10 : pageSize}" />
<c:set var="totalPage"     value="${empty totalPage    ? 1  : totalPage}" />
<c:set var="startPage"     value="${empty startPage    ? 1  : startPage}" />
<c:set var="endPage"       value="${empty endPage      ? 1  : endPage}" />
<c:set var="items"         value="${not empty noticeList ? noticeList : (not empty list ? list : null)}" />
<c:set var="searchKeyword" value="${empty searchKeyword ? '' : searchKeyword}" />
<c:set var="addNoticePath" value="${empty addNoticePath ? '/addNoticeView.do' : addNoticePath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>햇살소식(공지사항)</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>
  <style>
  
    /* 테이블만 최소 보강 */
    table{width:100%;border-collapse:collapse}
    th,td{border:1px solid var(--line);padding:10px}
    th{background:#f8f8f8}
    .panel{background: rgba(255,255,255,0.55);border: 1px solid var(--line);border-radius: 16px;overflow: hidden;
  	background-clip: padding-box; max-width: 1100px;margin: 24px auto;box-shadow: 0 8px 24px rgba(0,0,0,.12);
  	backdrop-filter: blur(12px) saturate(120%);-webkit-backdrop-filter: blur(12px) saturate(120%);}	/*동글동글라운드 불투명 유리효과*/
  	
    .panel-header{display:flex;justify-content:space-between;align-items:center;padding:12px 14px;border-bottom:1px solid var(--line)}
    .panel-title{font-size:18px;font-weight:700}
    .panel-body{padding:6px 14px}
    .panel-footer{display:flex;justify-content:space-between;align-items:center;padding:12px 14px;border-top:1px solid var(--line)}
    .searchbar{display:flex;gap:8px;align-items:center}
    .searchbar input[type=text]{padding:8px 10px;border:1px solid #ccc;border-radius:4px;min-width:280px}
    .btn{display:inline-block;border:1px solid #222;padding:8px 12px;text-decoration:none;color:#111;background:#fff;border-radius:4px}
    .btn.primary{background:#111;color:#fff}
    .pagination{display:flex;gap:6px}
    .pagination a,.pagination span{border:1px solid #ddd;padding:6px 10px;border-radius:4px;text-decoration:none;color:#111}
    .pagination .active{background:#111;color:#fff;border-color:#111}
    .empty{padding:28px;text-align:center;color:#666}
    .nav a.active span{font-weight:700;border-bottom:2px solid #111}
  </style>
</head>
<body>

<!-- ===== Topbar  ===== -->
<div class="topbar">
  <div class="container">
    <div class="brand">
      <a href="<c:url value='/'/>">온세상</a>
    </div>
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


  <!-- 목록 패널 -->
  <section class="panel">
    <div class="panel-header">
      <div class="panel-title">공지 목록</div>
      <form class="searchbar" method="get" action="<c:url value='/listNotice.do'/>">
        <label for="q">검색</label>
        <input type="hidden" name="currentPage" value="1"/>
        <input type="hidden" name="pageSize" value="${pageSize}"/>
        <input id="q" type="text" name="q" value="${fn:escapeXml(searchKeyword)}" placeholder="제목으로 검색" />
        <button class="btn" type="submit">검색</button>
      </form>
    </div>

    <div class="panel-body">
      <c:choose>
        <c:when test="${not empty items}">
          <table>
            <thead>
              <tr>
                <th style="width:120px;text-align:center">공지ID</th>
                <th>제목</th>
                <th style="width:220px;text-align:center">작성일시</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="it" items="${items}">
                <tr>
                  <td style="text-align:center"><c:out value="${it.noticeId}"/></td>
                  <td class="title">
                    <a href="<c:url value='/detailNotice.do'><c:param name='noticeId' value='${it.noticeId}'/></c:url>">
                      <c:out value="${it.title}"/>
                    </a>
                  </td>
                  <td style="text-align:center">
                    <c:out value="${it.createdAt}"/>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </c:when>
        <c:otherwise>
          <div class="empty">등록된 소식이 없습니다.</div>
        </c:otherwise>
      </c:choose>
    </div>

    <div class="panel-footer">
      <div class="pagination">
        <c:if test="${totalPage > 1}">
          <c:set var="q" value="${searchKeyword}" />
          <c:if test="${startPage > 1}">
            <a href="<c:url value='/listNotice.do'><c:param name='currentPage' value='${startPage-1}'/><c:param name='pageSize' value='${pageSize}'/><c:param name='q' value='${q}'/></c:url>">이전</a>
          </c:if>
          <c:forEach var="p" begin="${startPage}" end="${endPage}">
            <c:choose>
              <c:when test="${p == currentPage}">
                <span class="active">${p}</span>
              </c:when>
              <c:otherwise>
                <a href="<c:url value='/listNotice.do'><c:param name='currentPage' value='${p}'/><c:param name='pageSize' value='${pageSize}'/><c:param name='q' value='${q}'/></c:url>">${p}</a>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:if test="${endPage < totalPage}">
            <a href="<c:url value='/listNotice.do'><c:param name='currentPage' value='${endPage+1}'/><c:param name='pageSize' value='${pageSize}'/><c:param name='q' value='${q}'/></c:url>">다음</a>
          </c:if>
        </c:if>
      </div>

     

      <a class="btn primary" href="<c:url value='/addNoticeView.do'/>">등록</a>
    </div>
  </section>
</main>

<footer class="site-footer">© 온세상 조아조아~~</footer>

</body>
</html>
