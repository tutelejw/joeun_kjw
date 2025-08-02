package homework1;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginUser extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 이미 로그인한 사용자가 있는지 확인 (세션에 user 정보가 있는지 확인)
        HttpSession session = request.getSession(false); // 기존 세션 가져오기
        if (session != null && session.getAttribute("user") != null) {
            // 이미 로그인된 상태라면 로그인 절차를 건너뛰고, 로그인 상태로 처리
            out.println("<html><body>");
            out.println("<h3>이미 로그인된 사용자입니다.</h3>");
            out.println("<p><a href='viewUserInfo.jsp'>내 정보 보기</a></p>");
            out.println("<p><a href='logoutServlet'>로그아웃</a></p>");
            out.println("</body></html>");
            return;  // 로그인 처리하지 않고 바로 리턴
        }

        // 로그인 시도 (ID와 비밀번호 받기)
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        UserLoginPoolDAO userDAO = new UserLoginPoolDAO();
        UserVO userVO = userDAO.login(id, pwd); // 로그인 체크

        // 세션 생성 또는 가져오기
        session = request.getSession(true);  // 세션이 없으면 새로 생성

        if (userVO != null) {
            // 로그인 성공 시 세션에 사용자 정보 저장
            session.setAttribute("user", userVO);  // 로그인된 사용자 정보 세션에 저장
            out.println("<html><body>");
            out.println("<h3>" + userVO.getId() + " 님, 환영합니다!</h3>");
            out.println("<p><a href='viewUserInfo.jsp'>내 정보 보기</a></p>");
            out.println("<p><a href='logoutServlet'>로그아웃</a></p>");
            out.println("</body></html>");
            System.out.println("로그인 성공: " + id + " / " + pwd + "님, 환영합니다!");
        } else {
            // 로그인 실패 시
            out.println("<html><body>");
            out.println("<h3>로그인 실패: ID 또는 비밀번호가 잘못되었습니다.</h3>");
            out.println("<p><a href='addUser.html'>뒤로</a></p>");
            out.println("</body></html>");
            System.out.println("로그인 실패: " + id + " / " + pwd + " 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
