<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>손길나눔 목록조회</title>
  <link rel="stylesheet" href="/css/site.css" type="text/css">

  <!-- 추가: 테이블 스타일용 CSS -->
  <style>
    .center-table {
      margin: 20px auto;
      width: 100%;
      max-width: 1000px;
      table-layout: fixed;
      border-collapse: collapse;
      background-color: rgba(255,255,255,0.9);
    }

    .center-table th, .center-table td {
      padding: 10px;
      text-align: center;
      border: 1px solid #ccc;
      background-color: rgba(255,255,255,0.8);
      word-wrap: break-word;
    }

    .center-table th {
      background-color: rgba(200,200,200,0.85);
      font-weight: bold;
    }

    .header-search {
      margin: 20px auto;
      max-width: 1000px;
      display: flex;
      justify-content: flex-end; /* 검색 부분을 오른쪽으로 정렬 */
      align-items: center;
    }

    .header-search select,
    .header-search input[type="text"],
    .header-search input[type="submit"] {
      padding: 6px;
      font-size: 1em;
      margin-left: 10px; /* 간격 추가 */
    }

    /* 공통 컬럼 스타일 */
    .center-table .title-column {
      max-width: 600px; /* 제목 컬럼 길이 늘리기 */
    }

.center-table .postid-column,
    .center-table .region-column,
    .center-table .author-column,
    .center-table .status-column,
    .center-table .created-column {
      width: 90px; /* 공통으로 좁힘 */
    }

    /* 특별히 좁힐 컬럼만 추가로 조정 */
    .center-table .region-column {
      width: 120px; /* status 컬럼 좁히기 */
    }
    .center-table .status-column {
      width: 80px; /* status 컬럼 좁히기 */
    }

    .center-table .created-column {
      width: 160px; /* 생성일 컬럼 좁히기 */
    }
  </style>

  <script type="text/javascript">
    function fncGetUserList(pageNum){
      console.log("fncGetUserList called with pageNum =", pageNum);
      document.detailForm.currentPage.value = pageNum;
      document.detailForm.submit();
    }
  </script>
</head>

<body bgcolor="#ffffff" text="#000000">
<div style="width:98%; margin-left:10px;">

  <!--  상단 바 및 검색 부분 복원 -->
  <form name="detailForm" action="/listVolOffer.do" method="post">
    <input type="hidden" name="currentPage" value="${resultPage.currentPage}" />

    <div class="header-search">
      <!-- 추가: 검색 조건 요소 -->
      <select name="searchCondition">
        <option value="0">손길나눔ID</option>
        <option value="1">손길나눔명</option>
      </select>
      <input type="text" name="searchKeyword" placeholder="검색어 입력" />

      <!-- 검색 버튼 -->
      <input type="button" value="검색" onclick="fncGetUserList(1);" />
    </div>
    <!-- 여기까지 상단 바 및 검색 영역 복원 -->

    <!-- 테이블: 중앙 정렬 및 반투명 스타일 적용 -->
    <table class="center-table">
      <tr>
        <th class="postid-column">Post ID</th>
        <th class="title-column">제목</th>
        <th class="region-column">region</th>
        <th class="author-column">authorId</th>
        <th class="status-column">status</th>
        <th class="created-column">생성일</th>
      </tr>
      <c:set var="i" value="0" />
      <c:forEach var="volOffer" items="${list}">
        <c:set var="i" value="${i + 1}" />
        <tr>
          <td><a href="/getUser.do?userId=${volOffer.postId}">${volOffer.postId}</a></td>
          <td><a href="/getUser.do?userId=${volOffer.postId}">${volOffer.title}</a></td>
          <td>${volOffer.region}</td>
          <td>${volOffer.authorId}</td>
          <td>${volOffer.status}</td>
          <td>${volOffer.createdAt}</td>
        </tr>
      </c:forEach>
    </table>

    <!-- 페이지 네비게이터 포함 -->
    <table width="100%" style="margin-top:10px;">
      <tr>
        <td align="center">
          <jsp:include page="../common/pageNavigator.jsp"/>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
