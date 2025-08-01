package homework1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/UserSelect")
public class UserSelect extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id"); // ID 값이 비어 있으면 전체 조회

        UserLoginPoolDAO dao = new UserLoginPoolDAO();
        List<UserVO> users;

        if (id == null || id.trim().isEmpty()) {
            // 전체 사용자 조회
            users = dao.getAllUsers();
            out.println("<h2>전체 사용자 목록</h2>");
        } else {
            // 특정 사용자 조회
            UserVO user = dao.getUserById(id);
            if (user != null) {
                users = List.of(user);
                out.println("<h2>사용자 조회 결과</h2>");
            } else {
                users = null;
                out.println("<h2>해당 ID의 사용자가 존재하지 않습니다.</h2>");
            }
        }

        out.println("<html><body>");

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
