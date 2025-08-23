// com.semi.view.user.LoginAction.java
package com.semi.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.framework.Action;
import com.semi.service.user.UserService;
import com.semi.service.user.impl.UserServiceImpl;
import com.semi.domain.User;

public class LoginAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(userId, password);
            request.getSession().setAttribute("user", user);
            return "redirect:/index.jsp";                 // success: redirect
        } catch (IllegalArgumentException e) {
            request.setAttribute("loginError", e.getMessage());
            request.setAttribute("userId", userId);
            return "forward:/user/loginView.jsp";         // failure: forward (keeps attrs)
        }
    }
}
