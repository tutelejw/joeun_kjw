package controller;
import dao.UserDAO;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * ControlServlet - Front Controller
 * URL 패턴: *.do (web.xml 또는 어노테이션으로 매핑)
 *
 * 단순하고 교육용으로 설계: 각 요청을 나누어 처리하고, JSP로 포워드/리다이렉트
 */
 //@WebServlet("*.do")
public class ControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 폼 데이터 한글 처리 (필요 시)
        request.setCharacterEncoding("UTF-8");
        processRequest(request, response);
    }
    
    // 모든 요청을 처리하는 공용 메서드
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
        // 요청 URI에서 action 추출 (/UserProject/registerForm.do 등)
    	String uri = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String action = uri.substring(contextPath.length());
    	System.out.println("ControlServlet - processRequest 인입됨");
    	System.out.println("ControlServlet - processRequest 인입됨 : uri : " + uri);
    	System.out.println("ControlServlet - processRequest 인입됨 : contextPath : " + contextPath);
    	System.out.println("ControlServlet - processRequest 인입됨 : action : " + action);
    	try {
    		switch (action) {
    		case "/registerForm.do":
    			// 회원가입 폼을 JSP로 보여줌
    				forward(request, response, "/register.jsp");
    				break;
    		
    		case "/register.do":
    			//회원가입처리
    			System.out.println("ControlServlet - processRequest - register.do ");
    			System.out.println("call doRegister(request, response)");
    			doRegister(request, response);
    			break;
    			
    		case "/idCheck.do":
    			//아이디 중복 체크 (간단 응답)
    			doIdCheck(request, response);
    			break;
    		case "/loginForm.do":
                forward(request, response, "/login.jsp");
                break;

            case "/login.do":
                doLogin(request, response);
                break;

            case "/logout.do":
                doLogout(request, response);
                break;

            case "/welcome.do":
                // 로그인 상태 확인 후 환영 페이지
                forward(request, response, "/welcome.jsp");
                break;

            case "/editForm.do":
                // 개인정보 수정 폼 (본인 확인)
                forward(request, response, "/edit.jsp");
                break;

            case "/edit.do":
                doEdit(request, response);
                break;

            case "/userList.do":
                doUserList(request, response);
                break;

            case "/loginHistory.do":
                doLoginHistory(request, response);
                break;

            default:
                // 알려지지 않은 요청은 로그인 페이지로
                response.sendRedirect(request.getContextPath() + "/loginForm.do");
        }
    } catch (Exception e) {
        throw new ServletException(e);
    }
}
    // --- 각 액션별 메서드들 ---

    // 회원가입 처리
    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        
        System.out.println("ControlServlet _ doRegister : " + id +" / "+ password +" / " + name );
        User user = new User(id, password, name, "USER");
        int result = userDAO.registerUser(user);
        if (result == 1) {
            // 가입 성공 시 로그인 폼으로 이동
            response.sendRedirect(request.getContextPath() + "/loginForm.do");
        } else {
            // 실패 시 다시 폼으로 (간단히)
            response.sendRedirect(request.getContextPath() + "/registerForm.do");
        }
    }

    // 아이디 중복 체크 응답 (간단 텍스트). AJAX 또는 새창으로 호출 가능.
    private void doIdCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        boolean exists = userDAO.isIdExists(id);
        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write(exists ? "EXISTS" : "OK");
    }

    // 로그인 처리
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        User user = userDAO.login(id, password);
        if (user != null) {
            // 로그인 성공: 세션에 저장, 세션 타임아웃 설정(초 단위)
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(60 * 30); // 30분
            // 로그인 이력 저장 (IP 포함)
            String ip = request.getRemoteAddr();
            userDAO.insertLoginHistory(user.getId(), "LOGIN", ip);

            // 권한에 따라 환영 페이지 또는 관리자 페이지로 리다이렉트
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/userList.do");
            } else {
                response.sendRedirect(request.getContextPath() + "/welcome.do");
            }
        } else {
            // 로그인 실패 -> 로그인 폼으로 (간단 처리; 메시지 전달도 가능)
            response.sendRedirect(request.getContextPath() + "/loginForm.do");
        }
    }

    // 로그아웃 처리
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션에 저장된 유저아이디 기록 후 이력 남기기
            Object obj = session.getAttribute("user");
            if (obj instanceof User) {
                User u = (User) obj;
                String ip = request.getRemoteAddr();
                userDAO.insertLoginHistory(u.getId(), "LOGOUT", ip);
            }
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/loginForm.do");
    }

    // 회원정보 수정 처리
    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 로그인 상태 확인
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/loginForm.do");
            return;
        }
        User sessionUser = (User) session.getAttribute("user");

        String password = request.getParameter("password");
        String name = request.getParameter("name");

        // 본인만 수정 가능 (추가 체크)
        User userToUpdate = new User(sessionUser.getId(), password, name, sessionUser.getRole());
        int result = userDAO.updateUser(userToUpdate);
        if (result == 1) {
            // 세션 정보도 갱신
            sessionUser.setPassword(password);
            sessionUser.setName(name);
            session.setAttribute("user", sessionUser);
            response.sendRedirect(request.getContextPath() + "/welcome.do");
        } else {
            response.sendRedirect(request.getContextPath() + "/editForm.do");
        }
    }

    // 관리자용: 회원목록 조회
    private void doUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 관리자 권한 체크
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/loginForm.do");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            // 권한 없음
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 접근 가능합니다.");
            return;
        }

        // 전체 사용자 조회 후 JSP로 전달
        List<User> list = userDAO.getAllUsers();
        request.setAttribute("users", list);
        forward(request, response, "/userlist.jsp");
    }

    // 로그인 이력 조회 (관리자 전체 or 본인)
    private void doLoginHistory(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/loginForm.do");
            return;
        }
        User user = (User) session.getAttribute("user");

        // 관리자이면 전체 이력, 아니면 본인 이력만
        String queryUserId = null;
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            queryUserId = user.getId();
        }

        List<Map<String, Object>> history = userDAO.getLoginHistory(queryUserId);
        request.setAttribute("history", history);
        forward(request, response, "/loginhistory.jsp");
    }

    // 단순 포워드 헬퍼
    private void forward(HttpServletRequest request, HttpServletResponse response, String jspPath)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(jspPath);
        rd.forward(request, response);
    }
}
