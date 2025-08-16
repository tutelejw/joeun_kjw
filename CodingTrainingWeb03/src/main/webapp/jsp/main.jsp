
<!-- webapp/WEB-INF/jsp/main.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.academy.vo.UserVO" %>
<%
    UserVO loggedInUser = (UserVO) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect("login.do");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; }
        .main-container {
            padding: 50px;
        }
        .welcome-message {
            font-size: 1.5rem;
            color: #333;
        }
        .card-menu {
            margin-top: 30px;
        }
        .card-menu a {
            text-decoration: none;
            color: inherit;
        }
    </style>
</head>
<body>
    <div class="container main-container">
        <h1 class="mb-4">메인 페이지</h1>
        <p class="welcome-message">환영합니다, <%= loggedInUser.getUserId() %>님!</p>
        
        <div class="row card-menu">
            <div class="col-md-6 mb-4">
                <a href="gallery.do">
                    <div class="card shadow">
                        <div class="card-body text-center">
                            <h5 class="card-title">갤러리 보기</h5>
                            <p class="card-text text-muted">다른 사람들과 내 추억을 공유하고 감상하세요.</p>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-6 mb-4">
                <a href="upload.do">
                    <div class="card shadow">
                        <div class="card-body text-center">
                            <h5 class="card-title">사진 업로드</h5>
                            <p class="card-text text-muted">새로운 추억을 기록하고 업로드하세요.</p>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        
        <div class="text-center mt-5">
            <a href="logout.do" class="btn btn-danger">로그아웃</a>
        </div>
    </div>
</body>
</html>