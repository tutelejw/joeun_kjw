<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>homework1-addUser</title>
</head>
<body>
    <h2>회원가입</h2>
    <form method="post" action="/homework4/addUser.jsp">
        이름: <input type="text" name="id" required><br><br>
        비밀번호: <input type="password" name="pwd" required><br><br>

        성별:
        <input type="radio" name="gender" value="M" checked> 남자
        <input type="radio" name="gender" value="F"> 여자
        <br><br>

        결혼 여부:
        <select name="married">
            <option value="N" selected>미혼</option>
            <option value="Y">기혼</option>
        </select>
        <br><br>

        <input type="submit" value="회원가입">
    </form>

    <br><br>
    <p><a href="updateUserHtml.jsp">사용자조회 페이지 이동</a></p>
    <p><a href="findUserHtml.jsp">사용자조회 페이지 이동</a></p>
	<br>
</body>
</html>
