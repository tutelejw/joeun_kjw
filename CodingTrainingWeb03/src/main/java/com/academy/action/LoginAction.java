package com.academy.action;

import com.academy.dao.UserDAO;
import com.academy.vo.UserVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;

public class LoginAction implements Action {
    private static final Logger logger = Logger.getLogger(LoginAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");

        UserDAO userDAO = new UserDAO();
        UserVO user = userDAO.loginUser(userId, userPassword);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);
            logger.info("사용자 로그인 성공: " + userId);
            return "redirect:/main.do";
        } else {
            request.setAttribute("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
            logger.warn("로그인 실패: " + userId);
            return "/jsp/login.jsp";
        }
    }
}