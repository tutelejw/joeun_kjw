<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<html>
<head>
    <title>회원가입</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
        }
        .container {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>회원가입</h2>

    <!-- 오류 메시지가 있을 경우 출력 -->
    <c:if test="${not empty errorMessage}">
        <div class="error">
            ${errorMessage}
        </div>
    </c:if>

    <!-- 회원가입 폼 -->
    <form action="registerAction.do" method="post">
        <label for="id">아이디</label>
        <input type="text" id="id" name="id" required="required" placeholder="아이디를 입력하세요">
        
        <label for="password">비밀번호</label>
        <input type="password" id="password" name="password" required="required" placeholder="비밀번호를 입력하세요">
        
        <label for="name">이름</label>
        <input type="text" id="name" name="name" required="required" placeholder="이름을 입력하세요">
        
        <label for="age">나이</label>
        <input type="number" id="age" name="age" required="required" min="1" placeholder="나이를 입력하세요">

        <input type="submit" value="회원가입">
    </form>
</div>

</body>
</html>
