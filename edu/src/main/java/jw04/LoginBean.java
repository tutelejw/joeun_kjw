package jw04;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/LoginBean")
public class LoginBean extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        // 요청 및 응답 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 폼에서 받은 값
        String inputId = request.getParameter("id");
        String inputPwd = request.getParameter("pwd");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // DbBean을 통해 DB 연결
            conn = DbBean.getConnection();

            // ID 유무 확인
            String sql = "SELECT pwd FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, inputId);
            rs = pstmt.executeQuery();

            // 결과 처리
            out.println("<html><body>");
            if (!rs.next()) {
                out.println("<h3>" + inputId + " 는 존재하지 않는 ID입니다.</h3>");
                System.out.println("ID 오류: " + inputId);
            } else {
                String dbPwd = rs.getString("pwd");
                if (!dbPwd.equals(inputPwd)) {
                    out.println("<h3>비밀번호가 틀렸습니다.</h3>");
                    System.out.println("PW 오류: " + inputPwd);
                } else {
                    out.println("<h3>" + inputId + " 님, 환영합니다!</h3>");
                    System.out.println("로그인 성공: " + inputId);
                }
            }
            out.println("<p><p><a href='../loginBean.html'> 뒤로 </a>");
            //out.println("<p><p><a href='jw04/loginBean.html'> 뒤로 </a>");
            //out.println("<p><p><a href='/edu/jw04/loginBean.html'> 뒤로 </a>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<h3>오류 발생: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
}


//위 소스를 분석해서 servlet과 jdbc 연결을 분리해줘
//servlet 은 LoginBean.java 로
// jdbc 연결은 DbBean.java 로 작성해줘
//LoginBean.java 에서 폼 요청 처리까지 해야해
