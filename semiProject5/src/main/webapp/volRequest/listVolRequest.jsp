<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<title>봉사요청 목록</title>
<link rel="stylesheet" href="<c:url value='/css/site.css'/>">
<style>
.inline {
	display: inline-flex;
	align-items: center;
	gap: 8px;
}

.radio-wrap {
	display: flex;
	gap: 10px;
	flex-wrap: wrap;
	margin-top: 8px;
}

.radio-wrap label {
	border: 1px solid var(--line);
	padding: 6px 10px;
	border-radius: 999px;
	cursor: pointer;
}

.radio-wrap input {
	margin-right: 4px;
}
</style>
</head>

<script defer src="<c:url value='/js/listVolRequest.js'/>"></script>


<body>
	<%@ include file="/common/top.jspf"%>

	<form id="searchForm" method="get"
		action="<c:url value='/listVolRequest.do'/>"
		style="margin-bottom: 14px;">

		<div class="container-main">
			<div class="hero">
				<div class="title">마음부탁 (봉사요청)</div>

				<!-- ... 상단 지시문/스타일 동일 ... -->
				<select name="searchCondition" id="searchCondition"
					style="padding: 8px; border-radius: 10px; border: 1px solid var(--line);">
					<option value="title"
						<c:if test="${param.searchCondition=='title'}">selected</c:if>>제목</option>
					<%-- <option value="region"
						<c:if test="${param.searchCondition=='region'}">selected</c:if>>지역</option> --%>
					<option value="author"
						<c:if test="${param.searchCondition=='author'}">selected</c:if>>작성자</option>
					<option value="date"
						<c:if test="${param.searchCondition=='date'}">selected</c:if>>날짜</option>
				</select>


				<!-- 텍스트 검색(제목/지역) -->
				<input type="text" id="keywordText" name="searchKeyword"
					placeholder="검색어" value="${param.searchKeyword}"
					style="padding: 8px; border-radius: 10px; border: 1px solid var(--line); width: 260px;">

				<!-- 날짜 검색 -->
				<span id="dateGroup" style="display: none;"> <input
					type="date" id="dateFrom"
					style="padding: 8px; border-radius: 10px; border: 1px solid var(--line);">
					<span>~</span> <input type="date" id="dateTo"
					style="padding: 8px; border-radius: 10px; border: 1px solid var(--line);">
				</span>

				<!-- 날짜 검색 시 조합해서 넘길 hidden -->
				<input type="hidden" id="searchKeywordHidden" name="searchKeyword" />

				<input type="hidden" name="page"
					value="${empty param.page ? 1 : param.page}" />

				<button type="submit" class="btn">검색</button>

				<!-- 모집중만 체크 -->
				<label style="margin-left: 10px; font-weight: 600;"> <input
					type="checkbox" name="status" value="모집중"
					<c:if test="${param.status=='모집중'}">checked</c:if> /> 모집중만
				</label>

			</div>

			<!-- 카테고리 라디오 -->
			<div class="radio-wrap">
				<label><input type="radio" name="category" value=""
					<c:if test="${empty param.category}">checked</c:if>>전체</label>
				<c:set var="cats"
					value="동행,청소,장보기,배달,집수리,요리,밭일,미용,벌래잡기,소통대화,목욕,병원동행" />
				<c:forEach var="cname" items="${fn:split(cats, ',')}">
					<label> <input type="radio" name="category"
						value="${cname}"
						<c:if test="${param.category==cname}">checked</c:if> />${cname}
					</label>
				</c:forEach>
			</div>


			<!-- 목록/페이징 include -->
			<jsp:include page="/volRequest/_listTable.jsp" />
			<jsp:include page="/volRequest/_paging.jsp" />

		</div>
	</form>

	<%@ include file="/common/footer.jspf"%>

</body>
</html>
