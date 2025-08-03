package homework2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String gender = request.getParameter("gender");
        String married = request.getParameter("married");

        UserVO user = new UserVO();
        user.setId(id);
        user.setPwd(pwd);
        user.setGender(gender);
        user.setMarried(married);

        UserLoginPoolDAO dao = new UserLoginPoolDAO();
        dao.addUser(user);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>회원가입 완료</h2>");
        out.println("<p><a href='addUser.html'>다시 가입하기</a></p>");
        out.println("<p><a href='addUser.html'>메인페이지</a></p>");
        out.println("</body></html>");
    }
}
