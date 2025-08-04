<%@ page import="jw05.UserPoolDao" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");

    String inputId = request.getParameter("id");
    String inputPwd = request.getParameter("pwd");

    jw05.UserVO userVO = (jw05.UserVO)session.getAttribute("userVO");
    System.out.println("session에 저장된 UserVO 유무 확인 : " + userVO);

    if (!(inputId == null || inputId.equals(""))) {
        userVO = new jw05.UserVO();
        userVO.setId(inputId);
        userVO.setPwd(inputPwd);

        UserPoolDao userPoolDao = new UserPoolDao();
        userPoolDao.getUser(userVO);
    }
%>

<html lang="ko">
<head></head>
<body>
    <center><h2>LoginJSP Test</h2></center>

    <%
        if (userVO != null) {
    %>
        <%= userVO.getId() %> 님 환영합니다.
    <%
            session.setAttribute("userVO", userVO);
        } else {
    %>
        로그인실패 ID, PW 확인 하세요.
    <%
            System.out.println("로그인실패 ID, PW 확인 하세요.");
        }
    %>
    <p><p><a href='/edu/jw05/loginBeanDataSourceSessionJSP.html'> 뒤로 </a>
</body>
</html>
