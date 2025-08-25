<%-- /notice/updateNoticeView.jsp (새로 만들기) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지 수정</title>
  <link rel="stylesheet" href="<c:url value='/css/site.css'/>"/>
</head>
<body>
<main class="container-main" style="padding-top: 24px;">
    <form action="<c:url value='/updateNotice.do'/>" method="post">
        <input type="hidden" name="noticeId" value="${notice.noticeId}" />
        <h2>공지 수정</h2>
        <div class="form-group" style="margin-bottom: 12px;">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" class="form-control"
                   maxlength="100" required value="${notice.title}" />
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea id="content" name="content" class="form-control"
                      rows="10" maxlength="4000" required>${notice.content}</textarea>
        </div>
        <div style="margin-top: 12px; text-align: right;">
            <button type="submit" class="btn primary">수정 완료</button>
            <a href="<c:url value='/getNotice.do?noticeId=${notice.noticeId}'/>" class="btn">취소</a>
        </div>
    </form>
</main>
</body>
</html>