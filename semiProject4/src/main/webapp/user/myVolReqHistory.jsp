<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>내 봉사 요청 이력</title>
  <!-- 공통 CSS -->
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">

  <!-- 페이지 전용 보완 스타일 (구조/로직 변경 없음) -->
  <style>
  
  
    /* 배경: 은은한 그라데이션 + 패턴 */
   /* 배경: 고정 메인 이미지 + 은은한 오버레이 */
		html, body { height: 100%; }
		body {
		  margin: 0;
		  background: 
		    url("../images/main.jpg") no-repeat center center fixed,
		    radial-gradient(1200px 600px at 10% -10%, rgba(255,255,255,.25), transparent 60%),
		    radial-gradient(900px 500px at 110% 10%, rgba(255,255,255,.18), transparent 60%),
		    linear-gradient(180deg, #eef2f7 0%, #e9edf4 100%);
		  background-size: cover, auto, auto, auto;
		  -webkit-font-smoothing: antialiased;
		  -moz-osx-font-smoothing: grayscale;
		}


    /* 카드 컨테이너: 반투명 화이트 박스 */
    .form-wrap {
      max-width: 960px;
      margin: 48px auto 72px;
      padding: 28px 28px 32px;
      background: rgba(255, 255, 255, .86);
      backdrop-filter: blur(4px);
      border-radius: 16px;
      box-shadow:
        0 10px 24px rgba(16, 24, 40, .10),
        0 1px 3px rgba(16, 24, 40, .06);
      border: 1px solid rgba(0,0,0,.04);
    }

    .form-title {
      margin: 0;
      font-size: 22px;
      font-weight: 700;
      color: #1f2a44;
      letter-spacing: -.01em;
    }
    .form-sub {
      margin: 8px 0 18px;
      color: #5b6476;
      font-size: 14px;
    }

    /* 테이블 영역 */
    .form-wrap .table {
      width: 100%;
      border-collapse: separate;
      border-spacing: 0;
      background: #fff;
      border: 1px solid #e6e9ef;
      border-radius: 12px;
      overflow: hidden; /* 둥근 모서리 적용 */
    }
    .form-wrap .table thead th {
      background: #f6f8fb;
      color: #334155;
      text-align: left;
      font-weight: 600;
      font-size: 14px;
      padding: 14px 16px;
      border-bottom: 1px solid #e6e9ef;
      white-space: nowrap;
    }
    .form-wrap .table tbody td {
      padding: 14px 16px;
      font-size: 14px;
      color: #1f2937;
      border-bottom: 1px solid #eef1f6;
      vertical-align: middle;
    }
    .form-wrap .table tbody tr:last-child td {
      border-bottom: none;
    }

    /* 행 호버 / 지브라 */
    .form-wrap .table tbody tr:nth-child(even) {
      background: #fafbfd;
    }
    .form-wrap .table tbody tr:hover {
      background: #eef4ff;
    }

    /* 제목 컬럼: 한 줄 말줄임 */
    .form-wrap .table td a {
      display: inline-block;
      max-width: 100%;
      color: #0f5fff;
      text-decoration: none;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }
    .form-wrap .table td a:hover,
    .form-wrap .table td a:focus-visible {
      text-decoration: underline;
    }

    /* 번호/날짜 칼럼 정렬 보조 */
    .form-wrap .table td:first-child {
      text-align: center;
      color: #475569;
      font-variant-numeric: tabular-nums;
    }
    .form-wrap .table td:last-child {
      text-align: center;
      color: #475569;
      font-variant-numeric: tabular-nums;
      white-space: nowrap;
    }

    /* 마진 유틸 */
    .mt-16 { margin-top: 16px; }
    .mt-24 { margin-top: 24px; }

    /* 페이지네이션: 중앙 정렬, 작은 그림자 버튼 */
    .form-wrap .pager {
      display: flex;
      gap: .5rem;
      align-items: center;
      justify-content: center;
    }
    .form-wrap .pager .btn {
      display: inline-block;
      padding: 8px 14px;
      border-radius: 10px;
      line-height: 1;
      border: 1px solid #dbe2ea;
      background: #ffffff;
      box-shadow: 0 1px 1px rgba(16,24,40,.04);
      text-decoration: none;
      color: #0f172a;
      font-size: 14px;
    }
    .form-wrap .pager .btn:hover,
    .form-wrap .pager .btn:focus-visible {
      background: #f4f7ff;
      border-color: #c9d6ff;
    }
    .form-wrap .pager .status {
      font-size: 13px;
      color: #475569;
      padding: 0 6px;
      user-select: none;
    }

    /* 모바일 대응 */
    @media (max-width: 640px) {
      .form-wrap { padding: 20px; border-radius: 12px; }
      .form-wrap .table thead th:nth-child(3),
      .form-wrap .table tbody td:nth-child(3) {
        width: 120px !important;
      }
    }
  </style>
</head>
<body>
<div class="form-wrap">
  <h2 class="form-title">내 손길요청 흔적 보기</h2>
  <p class="form-sub">내가 작성한 봉사요청 글 목록입니다.</p>

  <!-- 페이징 기본값 -->
  <c:set var="p"        value="${empty page ? 1 : page}" />
  <c:set var="size"     value="${empty pageSize ? 10 : pageSize}" />
  <c:set var="total"    value="${empty total ? 0 : total}" />
  <c:set var="last"     value="${(total + size - 1) / size}" />
  <c:set var="startNo"  value="${total - ((p-1) * size)}" />

  <table class="table mt-16">
    <thead>
      <tr>
        <th style="width:80px;">순번</th>
        <th>타이틀</th>
        <th style="width:160px;">작성일</th>
      </tr>
    </thead>
    <tbody>
      <c:choose>
        <c:when test="${empty list}">
          <tr><td colspan="3" style="text-align:center;">작성한 요청이 없습니다.</td></tr>
        </c:when>
        <c:otherwise>
          <c:forEach var="r" items="${list}" varStatus="st">
            <tr>
              <td><c:out value="${startNo - st.index}"/></td>
              <td>
                <a href="<c:url value='/detailVolRequest.do'>
                           <c:param name='postId' value='${r.postId}'/>
                         </c:url>">
                  <c:out value="${r.title}"/>
                </a>
              </td>
              <td>
                <c:out value="${fn:substring(r.createdAt, 0, 7)}"/>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </tbody>
  </table>

  <!-- 페이지네이션 -->
  <div class="mt-24 pager">
    <c:if test="${p > 1}">
      <a class="btn" href="<c:url value='/myreqHistory.do'>
                             <c:param name='page' value='${p-1}'/>
                             <c:param name='size' value='${size}'/>
                           </c:url>">이전</a>
    </c:if>
    <span class="status">${p} / ${last}</span>
    <c:if test="${p < last}">
      <a class="btn" href="<c:url value='/myreqHistory.do'>
                             <c:param name='page' value='${p+1}'/>
                             <c:param name='size' value='${size}'/>
                           </c:url>">다음</a>
    </c:if>
  </div>
  
  
  
  
  
  
</div>
</body>
</html>























<%-- 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>내 봉사 요청 이력</title>
  <!-- 공통 CSS -->
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>">
</head>
<body>
<div class="form-wrap">
  <h2 class="form-title">내 손길요청 흔적 보기</h2>
  <p class="form-sub">내가 작성한 봉사요청 글 목록입니다.</p>

  <!-- 페이징 기본값 -->
  <c:set var="p"        value="${empty page ? 1 : page}" />
  <c:set var="size"     value="${empty pageSize ? 10 : pageSize}" />
  <c:set var="total"    value="${empty total ? 0 : total}" />
  <c:set var="last"     value="${(total + size - 1) / size}" />
  <c:set var="startNo"  value="${total - ((p-1) * size)}" />

  <table class="table mt-16">
    <thead>
      <tr>
        <th style="width:80px;">순번</th>
        <th>타이틀</th>
        <th style="width:160px;">작성일</th>
      </tr>
    </thead>
    <tbody>
      <c:choose>
        <c:when test="${empty list}">
          <tr><td colspan="3" style="text-align:center;">작성한 요청이 없습니다.</td></tr>
        </c:when>
        <c:otherwise>
          <c:forEach var="r" items="${list}" varStatus="st">
            <tr>
              <td><c:out value="${startNo - st.index}"/></td>
              <td>
                <a href="<c:url value='/detailVolRequest.do'>
                           <c:param name='postId' value='${r.postId}'/>
                         </c:url>">
                  <c:out value="${r.title}"/>
                </a>
              </td>
              <td>
                <c:out value="${fn:substring(r.createdAt, 0, 10)}"/>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </tbody>
  </table>

  <!-- 페이지네이션 -->
  <div class="mt-24" style="display:flex; gap:.5rem; align-items:center; justify-content:center;">
    <c:if test="${p > 1}">
      <a class="btn" href="<c:url value='/myreqHistory.do'>
                             <c:param name='page' value='${p-1}'/>
                             <c:param name='size' value='${size}'/>
                           </c:url>">이전</a>
    </c:if>
    <span>${p} / ${last}</span>
    <c:if test="${p < last}">
      <a class="btn" href="<c:url value='/myreqHistory.do'>
                             <c:param name='page' value='${p+1}'/>
                             <c:param name='size' value='${size}'/>
                           </c:url>">다음</a>
    </c:if>
  </div>
</div>
</body>
</html>
--%>
