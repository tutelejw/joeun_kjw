<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="homework4.*" %>

<%
    // 세션에서 현재 로그인한 사용자 정보 가져오기
    UserVO user = (UserVO) session.getAttribute("userVO");
    if (user == null) {
%>
        <p>로그인이 필요합니다.</p>
        <a href="addUserHtml.jsp">메인으로</a>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
</head>
<body>
    <h2>회원 정보 수정</h2>
    <form method="post" action="updateUser.jsp">
        이름: <input type="text" name="id" value="<%= user.getId() %>" readonly><br><br>
        비밀번호: <input type="password" name="pwd" value="<%= user.getPwd() %>" required><br><br>

        성별:
        <input type="radio" name="gender" value="M" <%= user.getGender().equals("M") ? "checked" : "" %>> 남자
        <input type="radio" name="gender" value="F" <%= user.getGender().equals("F") ? "checked" : "" %>> 여자
        <br><br>

        결혼 여부:
        <select name="married">
            <option value="N" <%= user.getMarried().equals("N") ? "selected" : "" %>>미혼</option>
            <option value="Y" <%= user.getMarried().equals("Y") ? "selected" : "" %>>기혼</option>
        </select>
        <br><br>

        <input type="submit" value="정보 수정">
    </form>

    <br><br>
    <p><a href="findUserHtml.jsp">사용자조회 페이지 이동</a></p>
</body>
</html>
