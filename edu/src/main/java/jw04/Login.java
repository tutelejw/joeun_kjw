package jw04;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/edu/Login")

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String inputId = request.getParameter("id");
        String inputPwd = request.getParameter("pwd");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Oracle JDBC 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // DB 연결
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@127.0.0.1:1521:xe", "scott", "tiger");

            // 1. ID 존재 확인
            String sqlIdCheck = "SELECT pwd FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sqlIdCheck);
            pstmt.setString(1, inputId);
            rs = pstmt.executeQuery();

            if (!rs.next()) {
                out.println("<h3>" + inputId + "가 잘못되었습니다.</h3>");
                System.out.println("ID가 잘못되었습니다 " + inputId);
            } else {
                String dbPwd = rs.getString("pwd");
                if (!dbPwd.equals(inputPwd)) {
                    out.println("<h3>PW가 잘못되었습니다.</h3>");
                    System.out.println("PW가 잘못되었습니다 " + inputPwd);
                } else {
                    out.println("<h3>" + inputId + " 님 환영합니다.</h3>");
                    System.out.println("로그인됨 " +inputId+" / "+ inputPwd);
                }
            }

        } catch (Exception e) {
            out.println("<h3>오류 발생: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch(Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if (conn != null) conn.close(); } catch(Exception e) {}
        }
    }
}

//
//login.html 페이지를 통해서 oracle database 테이블 데이터로 로그인하는 login java servlet 파일 작성해줘.
//oracle 정보 127.0.0.1:1521:xe    /  id:scott / pw:tiger
//id 확인과 pwd 확인은 각각 해주고 두개 데이터가 정확하면 로그인 하고 응답해줘
//로그인 정상시 "ID" 님 환영합니다.
//로그인 실패시 "ID"가 잘못되었습니다. "PW"가 잘못되었습니다. 
//서버에도 로그 가 남도록 해줘

