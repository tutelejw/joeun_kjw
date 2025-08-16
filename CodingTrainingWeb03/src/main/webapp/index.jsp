<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- webapp/index.jsp -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>환영합니다!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .welcome-card {
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 500px;
            text-align: center;
        }
        .btn-custom {
            margin: 10px;
            width: 150px;
            font-weight: bold;
            border-radius: 50px;
            transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
        }
        .btn-custom:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="welcome-card">
        <h1 class="mb-3">추억의 아카데미에 오신 것을 환영합니다!</h1>
        <p class="lead text-muted mb-4">로그인하거나 회원가입하여 소중한 추억을 공유해 보세요.</p>
        <a href="login.do" class="btn btn-primary btn-lg btn-custom">로그인</a>
        <a href="joinForm.do" class="btn btn-secondary btn-lg btn-custom">회원가입</a>
    </div>
</body>
</html>