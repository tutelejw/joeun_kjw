<!-- webapp/WEB-INF/jsp/joinForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f2f5; }
        .join-container {
            max-width: 500px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <div class="container join-container">
        <h2 class="text-center mb-4">회원가입</h2>
        <form action="join.do" method="post">
            <div class="mb-3">
                <label for="userId" class="form-label">아이디</label>
                <input type="text" class="form-control" id="userId" name="userId" required>
            </div>
            <div class="mb-3">
                <label for="userPassword" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="userPassword" name="userPassword" required>
            </div>
            <div class="mb-3">
                <label for="userName" class="form-label">이름</label>
                <input type="text" class="form-control" id="userName" name="userName" required>
            </div>
            <div class="mb-3">
                <label for="userAge" class="form-label">나이</label>
                <input type="number" class="form-control" id="userAge" name="userAge" required>
            </div>
            <div class="mb-3">
                <label for="userAddress" class="form-label">주소</label>
                <input type="text" class="form-control" id="userAddress" name="userAddress" required>
            </div>
            <div class="mb-3">
                <label for="memoryTitle" class="form-label">메모리 타이틀</label>
                <input type="text" class="form-control" id="memoryTitle" name="memoryTitle" required>
            </div>
            <% if (request.getAttribute("message") != null) { %>
                <div class="alert alert-warning" role="alert">
                    <%= request.getAttribute("message") %>
                </div>
            <% } %>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-primary">가입하기</button>
            </div>
        </form>
    </div>
</body>
</html>