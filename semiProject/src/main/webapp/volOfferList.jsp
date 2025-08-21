<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="volOfferList" value="${empty volOfferList ? emptyList : volOfferList}" />

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>봉사제공 목록</title>
  <link rel="stylesheet" href="https://cdn.tailwindcss.com" type="text/css">
  <style>
    .wrap { max-width: 1080px; margin: 24px auto; }
    .topbar { display:flex; justify-content: space-between; align-items:center; margin-bottom:12px; }
    table { width:100%; border-collapse: collapse; }
    th, td { border-bottom:1px solid #e5e7eb; padding:10px; text-align:left; }
    th { background:#f9fafb; }
    .pill { padding:4px 8px; border-radius:999px; font-size:12px; }
    .pill.green { background:#dcfce7; }
    .pill.gray { background:#e5e7eb; }
    .btn { padding:8px 12px; border-radius:10px; border:0; background:#16a34a; color:#fff; text-decoration:none; }
    .muted { color:#6b7280; font-size:12px; }
  </style>
</head>
<body>
<div class="wrap">
  <div class="topbar">
    <h2>봉사제공 목록</h2>
    <a class="btn" href="/volOfferAdd.jsp">등록하기</a>
  </div>

  <table>
    <thead>
      <tr>
        <th style="width:90px;">글번호</th>
        <th>제목</th>
        <th style="width:140px;">작성자</th>
        <th style="width:220px;">기간</th>
        <th style="width:140px;">지역</th>
        <th style="width:140px;">카테고리</th>
        <th style="width:130px;">제공일</th>
        <th style="width:130px;">상태</th>
        <th style="width:110px;">상세</th>
      </tr>
    </thead>
    <tbody>
    <c:choose>
      <c:when test="${not empty volOfferList}">
        <c:forEach var="it" items="${volOfferList}">
          <tr>
            <td>${it.postId}</td>
            <td>${it.title}</td>
            <td>${it.authorId}</td>
            <td>
              <div>${it.startTime}</div>
              <div class="muted">~ ${it.endTime}</div>
            </td>
            <td>${it.region}</td>
            <td>${it.category}</td>
            <td>${it.offerDate}</td>
            <td>
              <c:choose>
                <c:when test="${it.processStatus eq 'COMPLETED'}"><span class="pill green">완료</span></c:when>
                <c:otherwise><span class="pill gray">${it.processStatus}</span></c:otherwise>
              </c:choose>
            </td>
            <td><a href="/getVolOffer.do?postId=${it.postId}">보기</a></td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr><td colspan="9" style="text-align:center; padding:24px;">등록된 글이 없습니다.</td></tr>
      </c:otherwise>
    </c:choose>
    </tbody>
  </table>

  <!-- (선택) 페이징 표기: 컨트롤러에서 값 주면 표시됨 -->
  <c:if test="${not empty totalPage}">
    <div style="margin-top:14px; display:flex; gap:8px; justify-content:center;">
      <c:forEach var="p" begin="1" end="${totalPage}">
        <c:choose>
          <c:when test="${p == currentPage}">
            <span class="pill gray">${p}</span>
          </c:when>
          <c:otherwise>
            <a class="pill" href="/listVolOffer.do?currentPage=${p}">${p}</a>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </div>
  </c:if>
</div>
</body>
</html>
