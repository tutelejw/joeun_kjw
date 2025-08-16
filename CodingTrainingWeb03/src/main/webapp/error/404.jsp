<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- webapp/error/404.jsp -->
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 Not Found</title>
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
        .error-card {
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            padding: 40px;
            max-width: 500px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="error-card">
        <h1 class="display-1 text-danger">404</h1>
        <h2>페이지를 찾을 수 없습니다.</h2>
        <p class="mt-3">요청하신 페이지가 존재하지 않거나, 이동되었을 수 있습니다.</p>
        <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-primary mt-4">홈으로 돌아가기</a>
    </div>
</body>
</html>