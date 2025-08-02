package homework1;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginUser2 extends HttpServlet {
    @Override
    protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        UserLoginPoolDAO userDAO = new UserLoginPoolDAO();
        UserVO userVO = userDAO.login(id, pwd);

        // 세션 생성 또는 가져오기
        HttpSession session = request.getSession(true);  // true로 호출하면 세션이 없으면 새로 생성됩니다.

        // 세션 ID 출력 (System.out.println)
        System.out.println("세션 ID: " + session.getId());  // 세션 ID 출력
        
        out.println("<html><body>");
        if (userVO == null) {
            out.println("<h3>로그인 실패: ID 또는 비밀번호가 잘못되었습니다.</h3>");
            System.out.println("로그인 실패: " + id + " / " + pwd +"또는 비밀번호가 잘못되었습니다.");
        } else {
        	// 로그인 성공 시 세션에 사용자 정보 저장
            session.setAttribute("user", userVO);
        	out.println("<h3>" + userVO.getId() + " 님, 환영합니다!</h3>");
            System.out.println("로그인 성공: " + id + " / " + pwd +"님, 환영합니다!.");
        }
        out.println("<p><a href='addUser.html'>뒤로</a>");
        out.println("</body></html>");
    }
}