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
        
        HttpSession session = request.getSession();
        session.setAttribute("userVO", user);  // 전체 사용자 객체 저장
        //session.setAttribute("id", user.getId());  // 또는 id만 따로 저장해도 됨
        System.out.println(" jsessionid = "+ session.getId() );

        out.println("<html><body>");
        out.println("<h2>회원가입 완료</h2>");
        out.println("<p><a href='findUser.html'>사용자 조회 하기</a></p>");
        out.println("<p><a href='addUser.html'>메인페이지</a></p>");
        out.println("</body></html>");
    }
}
