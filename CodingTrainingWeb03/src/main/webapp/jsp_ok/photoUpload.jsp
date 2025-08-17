
<!-- webapp/WEB-INF/jsp/photoUpload.jsp -->
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
    <title>사진 업로드</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; }
        .upload-container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
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
                        <a class="nav-link" href="gallery.do">갤러리</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="upload.do">사진 업로드</a>
                    </li>
                </ul>
                <a href="logout.do" class="btn btn-outline-light">로그아웃</a>
            </div>
        </div>
    </nav>
    <div class="container upload-container">
        <h2 class="text-center mb-4">사진 업로드</h2>
        <form action="upload.do" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="photoFile" class="form-label">사진 파일</label>
                <input type="file" class="form-control" id="photoFile" name="photoFile" required>
            </div>
            <div class="mb-3">
                <label for="photoDescription" class="form-label">설명</label>
                <textarea class="form-control" id="photoDescription" name="photoDescription" rows="3"></textarea>
            </div>
            <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-info" role="alert">
                    <%= request.getAttribute("message") %>
                </div>
            <% } %>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">업로드</button>
            </div>
        </form>
    </div>
</body>
</html>
