package com.semi.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.semi.framework.Action;

public class UpdatePwdViewAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "/user/loginView.jsp";    // TODO: adjust path
        }

        // When user clicks "비밀번호변경" in 마이페이지,
        // show the current-password confirmation page first.
        return "/user/confirmPwd.jsp";       // TODO: adjust path
    }
}
