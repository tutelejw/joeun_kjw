<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>homework4-findUser</title>
</head>
<body>
    <h2>사용자조회a</h2>
    <form method="post" action="/homework4/findUser.jsp">
        사용자 ID: <input type="text" name="id" required><br><br>
        <input type="submit" value="사용자 조회">
    </form>
    <br>
    <p><a href="addUserHtml.jsp">회원가입 페이지로 이동</a></p>
</body>
</html>
