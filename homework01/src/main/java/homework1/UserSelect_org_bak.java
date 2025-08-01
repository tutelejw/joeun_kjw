package homework1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/UserSelect")
public class UserSelect_org_bak extends HttpServlet {

    @Override
    //protected void doGet(HttpServletRequest request, HttpServletResponse response)
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserDAO dao = new UserDAO();
        List<UserVO> users = dao.getAllUsers();

        out.println("<html><body>");
        out.println("<h2>사용자 목록</h2>");

        if (users == null || users.isEmpty()) {
            out.println("<p>사용자가 없습니다.</p>");
        } else {
            out.println("<table border='2'>");
            out.println("<tr><th>번호</th><th>ID</th><th>비밀번호</th><th>성별</th><th>결혼여부</th></tr>");
            for (UserVO user : users) {
                out.println("<tr>");
                out.println("<td>" + user.getNo() + "</td>");
                out.println("<td>" + user.getId() + "</td>");
                out.println("<td>" + user.getPwd() + "</td>");
                out.println("<td>" + user.getGender() + "</td>");
                out.println("<td>" + user.getMarried() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("<br><a href='addUser.html'>메인 페이지로</a>");
        out.println("</body></html>");
    }
}
