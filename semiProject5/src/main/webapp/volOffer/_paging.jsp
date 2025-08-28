<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="paging-wrap"
     style="margin-top:12px; display:flex; align-items:center; gap:12px;">
  
  <!-- 왼쪽: 페이징 네비게이션 -->
  <div class="paging-nav" style="flex:1;">
    <c:url var="baseUrl" value="/listVolRequest.do">
      <c:param name="searchCondition" value="${param.searchCondition}"/>
      <c:param name="searchKeyword"   value="${param.searchKeyword}"/>
      <c:param name="category"        value="${param.category}"/>
      <c:param name="status"          value="${param.status}"/>
    </c:url>

    <c:if test="${page.maxPage > 1}">
      <!-- 이전 묶음 -->
      <c:if test="${page.beginUnitPage > 1}">
        <a class="btn" href="${baseUrl}&page=${page.beginUnitPage - 1}">&laquo;</a>
      </c:if>

      <!-- 페이지 번호 -->
      <c:forEach var="p" begin="${page.beginUnitPage}" end="${page.endUnitPage}">
        <a class="btn" href="${baseUrl}&page=${p}"
           style="margin:0 3px; ${p==page.currentPage ? 'font-weight:700; text-decoration:underline;' : ''}">
           ${p}
        </a>
      </c:forEach>

      <!-- 다음 묶음 -->
      <c:if test="${page.endUnitPage < page.maxPage}">
        <a class="btn" href="${baseUrl}&page=${page.endUnitPage + 1}">&raquo;</a>
      </c:if>
    </c:if>
  </div>

  <!-- 오른쪽: 등록 버튼 (로그인 시에만 표시) -->
  <div class="paging-actions" style="margin-left:auto;">
    <c:if test="${not empty sessionScope.user}">
      <a href="<c:url value='/addVolOfferView.do'/>" class="btn">등록</a>
    </c:if>
  </div>
</div>

