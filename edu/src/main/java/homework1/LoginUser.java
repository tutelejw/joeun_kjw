package homework1;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/edu/loginBeanPool")
public class LoginUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        UserLoginPoolDAO userDAO = new UserLoginPoolDAO();
        UserVO userVO = userDAO.login(id, pwd);

        out.println("<html><body>");
        if (userVO == null) {
            out.println("<h3>로그인 실패: ID 또는 비밀번호가 잘못되었습니다.</h3>");
            System.out.println("로그인 실패: " + id + " / " + pwd +"또는 비밀번호가 잘못되었습니다.");
        } else if (!"Y".equals(userVO.getActive())) {
            out.println("<h3>로그인 실패: 비활성화된 계정입니다.</h3>");
        } else {
            out.println("<h3>" + userVO.getId() + " 님, 환영합니다!</h3>");
            System.out.println("로그인 성공: " + id + " / " + pwd +"님, 환영합니다!.");
        }
        out.println("<p><a href='/edu/homework1/addUser.html'>뒤로</a>");
        out.println("</body></html>");
    }
}