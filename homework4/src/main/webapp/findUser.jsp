<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%-- <%@ page import="homework4.*" %>  --%>
<%@ page import="jw.service.user.vo.UserVO" %>
<%@ page import="jw.service.user.dao.UserDAO" %>

<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");

    //HttpSession session = request.getSession(true);

    if (id == null) {
        UserVO sessionUser = (UserVO) session.getAttribute("userVO");
        if (sessionUser != null) {
            id = sessionUser.getId();
            System.out.println("jsessionid = " + session.getId());
        } else {
            out.println("<html><body>");
            out.println("<p>세션에 사용자 정보가 없습니다. 로그인해주세요.</p>");
            out.println("<a href='addUserHtml.jsp'>로그인 페이지로 이동</a>");
            out.println("</body></html>");
            return;
        }
    }

    //UserLoginPoolDAO dao = new UserLoginPoolDAO();
    UserDAO dao = new UserDAO();
    UserVO userVO = dao.getUserById(id);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>User Select</title>
</head>
<body>
    <h2>사용자 정보 조회</h2>

<%
    if (userVO == null) {
%>
        <p><%= id %> 에 해당하는 사용자 정보가 없습니다.</p>
<%
    } else {
%>
        <p>no       : <%= userVO.getNo() %></p>
        <p>id       : <%= userVO.getId() %></p>
        <p>pwd      : <%= userVO.getPwd() %></p>
        <p>gender   : <%= userVO.getGender() %></p>
        <p>married  : <%= userVO.getMarried() %></p>
<%
    }
%>

    <br><a href="findUserHtml.jsp">이전 페이지로</a>
</body>
</html>
