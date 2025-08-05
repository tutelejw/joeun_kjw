<%@ page contentType="text/html; charset=UTF-8" %>
<%-- <%@ page import="homework4.*" %>  --%>
<%@ page import="jw.service.user.dao.UserDAO" %>
<%@ page import="jw.service.user.vo.UserVO" %>


<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    String gender = request.getParameter("gender");
    String married = request.getParameter("married");

    // 사용자 객체 생성 및 값 세팅
    UserVO user = new UserVO();
    user.setId(id);
    user.setPwd(pwd);
    user.setGender(gender);
    user.setMarried(married);

    // DB 업데이트 처리
    UserDAO dao = new UserDAO();
    boolean success = dao.updateUser(user); 

    // 세션 정보 갱신
    session.setAttribute("userVO", user);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정 완료</title>
</head>
<body>
    <h2>회원 정보 수정 결과</h2>
    <%
        if (success) {
    %>
        <p>수정이 완료되었습니다.</p>
        <p>ID: <%= id %></p>
        <p><a href="findUserHtml.jsp">사용자 조회 하기</a></p>
    <%
        } else {
    %>
        <p>수정 실패. 다시 시도해 주세요.</p>
        <p><a href="updateUserHtml.jsp">돌아가기</a></p>
    <%
        }
    %>
</body>
</html>
