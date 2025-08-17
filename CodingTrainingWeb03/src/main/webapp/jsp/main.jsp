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
        .menu-list {
            list-style: none;
            padding: 0;
            margin-top: 30px;
        }
        .menu-list li {
            margin-bottom: 10px;
        }
        .menu-list a {
            display: block;
            padding: 15px 20px;
            background-color: #fff;
            border-radius: 10px;
            text-decoration: none;
            color: #333;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            transition: transform 0.2s, background-color 0.2s;
        }
        .menu-list a:hover {
            background-color: #e9ecef;
            transform: translateX(10px);
        }
        .menu-list .menu-title {
            font-weight: bold;
        }
        .menu-list .menu-description {
            font-size: 0.9rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="main.do">추억의 아카데미</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" href="main.do">메인</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gallery.do">갤러리</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="upload.do">사진 업로드</a>
                    </li>
                </ul>
                <a href="logout.do" class="btn btn-outline-light">로그아웃</a>
            </div>
        </div>
    </nav>
    <div class="container main-container">
        <h1 class="mb-4">메인 페이지</h1>
        <p class="welcome-message">환영합니다, <%= loggedInUser.getUserId() %>님!</p>
        
        <ul class="menu-list">
            <li>
                <a href="gallery.do">
                    <span class="menu-title">갤러리 보기</span><br>
                    <span class="menu-description">다른 사람들과 내 추억을 공유하고 감상하세요.</span>
                </a>
            </li>
            <li>
                <a href="upload.do">
                    <span class="menu-title">사진 업로드</span><br>
                    <span class="menu-description">새로운 추억을 기록하고 업로드하세요.</span>
                </a>
            </li>
        </ul>
        
        <div class="text-center mt-5">
            <a href="logout.do" class="btn btn-danger">로그아웃</a>
        </div>
    </div>
</body>
</html>