<!-- webapp/WEB-INF/jsp/gallery.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.academy.vo.PhotoVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.academy.vo.UserVO" %>
<%
    UserVO loggedInUser = (UserVO) session.getAttribute("loggedInUser");
    if (loggedInUser == null) {
        response.sendRedirect("login.do");
        return;
    }
    List<PhotoVO> photoList = (List<PhotoVO>) request.getAttribute("photoList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>갤러리</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; }
        .gallery-container {
            padding: 50px;
        }
        .photo-card {
            border: none;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }
        .photo-card:hover {
            transform: scale(1.03);
        }
        .photo-img {
            width: 100%;
            height: 250px;
            object-fit: cover;
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
                        <a class="nav-link active" href="gallery.do">갤러리</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="upload.do">사진 업로드</a>
                    </li>
                </ul>
                <a href="logout.do" class="btn btn-outline-light">로그아웃</a>
            </div>
        </div>
    </nav>
    <div class="container gallery-container">
        <h1 class="mb-4">갤러리</h1>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <% if (photoList != null && !photoList.isEmpty()) { %>
                <% for (PhotoVO photo : photoList) { %>
                    <div class="col">
                        <div class="card photo-card">
                            <img src="photos/<%= photo.getPhotoPath() %>" class="card-img-top photo-img" alt="<%= photo.getPhotoDescription() %>">
                            <div class="card-body">
                                <h5 class="card-title">작성자: <%= photo.getUserId() %></h5>
                                <p class="card-text"><%= photo.getPhotoDescription() %></p>
                                <p class="card-text"><small class="text-muted"><%= photo.getPhotoDate() %></small></p>
                            </div>
                        </div>
                    </div>
                <% } %>
            <% } else { %>
                <div class="col">
                    <p>아직 등록된 사진이 없습니다.</p>
                </div>
            <% } %>
        </div>
    </div>
</body>
</html>