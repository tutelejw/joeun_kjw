<!-- webapp/WEB-INF/jsp/login.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; }
        .login-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <div class="container login-container">
        <h2 class="text-center mb-4">로그인</h2>
        <form action="login.do" method="post">
            <div class="mb-3">
                <label for="userId" class="form-label">아이디</label>
                <input type="text" class="form-control" id="userId" name="userId" required>
            </div>
            <div class="mb-3">
                <label for="userPassword" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="userPassword" name="userPassword" required>
            </div>
            <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getAttribute("message") %>
                </div>
            <% } %>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">로그인</button>
            </div>
        </form>
        <div class="text-center mt-3">
            <a href="joinForm.do">회원가입</a>
        </div>
    </div>
</body>
</html>