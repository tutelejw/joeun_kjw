package jw04;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginBeanDAO extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String inputId = request.getParameter("id");
        String inputPwd = request.getParameter("pwd");

        try {
            UserDAO dao = new UserDAO();
            UserVO user = dao.getUserById(inputId);

            out.println("<html><body>");
            if (user == null) {
                out.println("<h3>" + inputId + " 는 존재하지 않는 ID입니다.</h3>");
            } else if (!user.getPwd().equals(inputPwd)) {
                out.println("<h3>비밀번호가 틀렸습니다.</h3>");
            } else if (!user.isActive()) {
                out.println("<h3>이 계정은 비활성 상태입니다. 로그인할 수 없습니다.</h3>");
            } else {
                out.println("<h3>" + inputId + " 님, 환영합니다!</h3>");
            }
            out.println("<p><p><a href='../loginBean.html'> 뒤로 </a>");
            out.println("</body></html>");

        } catch (Exception e) {
            out.println("<h3>오류 발생: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}