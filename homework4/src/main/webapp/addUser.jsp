<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="homework4.*" %>
<%@ page import="java.io.*" %>

<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    String gender = request.getParameter("gender");
    String married = request.getParameter("married");

    // 객체 생성 및 저장
    UserVO user = new UserVO();
    user.setId(id);
    user.setPwd(pwd);
    user.setGender(gender);
    user.setMarried(married);

    // DAO 호출
    UserLoginPoolDAO dao = new UserLoginPoolDAO();
    dao.addUser(user);

    // 세션에 사용자 저장
    //HttpSession session = request.getSession();
    session.setAttribute("userVO", user);
    System.out.println(" jsessionid = " + session.getId());
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입 완료</title>
</head>
<body>
    <h2>회원가입 완료</h2>
    <p>사용자 ID: <%= id %></p>
    
    <p><a href="findUserHtml.jsp">사용자 조회 하기</a></p>
    <p><a href="addUserHtml.jsp">메인페이지</a></p>
    
</body>
</html>
