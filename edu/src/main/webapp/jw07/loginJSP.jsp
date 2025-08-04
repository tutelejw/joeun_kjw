<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>

<%
    request.setCharacterEncoding("UTF-8");

    String inputId = request.getParameter("id");
    String inputPwd = request.getParameter("pwd");

    String fromDbId = null;
    String fromDbPwd = null;

    Connection con = null;

    try {
        String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbuser = "scott";
        String dbpwd = "tiger";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(dburl, dbuser, dbpwd);

        PreparedStatement pStmt = con.prepareStatement("SELECT id, pwd FROM users WHERE id = ?");
        pStmt.setString(1, inputId);

        ResultSet rs = pStmt.executeQuery();

        if (rs.next()) {
            fromDbId = rs.getString("id");
            fromDbPwd = rs.getString("pwd");
            System.out.println("DB 확인: " + fromDbId + " / " + fromDbPwd);
        } else {
            System.out.println("해당 ID 없음: " + inputId);
        }

        rs.close();
        pStmt.close();
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>LoginJSP Test</title>
</head>
<body>
    <center><h2>LoginJSP Test</h2></center>

    <%
    	// inputId나 inputPwd가 null일 수 있다고 판단하면 내부적으로 JSP 서블릿 컴파일 중 컴파일러 오류로 처리
        //if (fromDbId != null && fromDbPwd != null && fromDbId.equals(inputId) && fromDbPwd.equals(inputPwd)) {
        if (fromDbId != null && fromDbPwd != null && inputId != null && inputPwd != null && fromDbId.equals(inputId) && fromDbPwd.equals(inputPwd)){
    %>
        <%= inputId %> 님 환영합니다.
    <%
        } else {
    %>
        id 또는 pwd를 확인하세요.
    <%
        }
    %>

    <p/><p/><a href='/edu/jw07/loginJSP.jsp'>뒤로</a>
</body>
</html>
