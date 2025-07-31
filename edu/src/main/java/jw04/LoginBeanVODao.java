package jw04;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/edu/LoginBeanVODao")
public class LoginBeanVODao extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        UserDAO userDAO = new UserDAO();
        UserVO userVO = userDAO.login(id, pwd);

        out.println("<html><body>");
        if (userVO == null) {
            out.println("<h3>로그인 실패: ID 또는 비밀번호가 잘못되었습니다.</h3>");
        } else if (!"Y".equals(userVO.getActive())) {
            out.println("<h3>로그인 실패: 비활성화된 계정입니다.</h3>");
        } else {
            out.println("<h3>" + userVO.getId() + " 님, 환영합니다!</h3>");
        }
        out.println("<p><a href='/edu/jw04/loginBean.html'>뒤로</a>");
        out.println("</body></html>");
    }
}