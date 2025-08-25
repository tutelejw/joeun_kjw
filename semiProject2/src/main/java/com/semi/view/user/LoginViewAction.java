// src/com/semi/view/user/LoginViewAction.java
package com.semi.view.user;

import javax.servlet.http.*;
import com.semi.framework.Action;

public class LoginViewAction extends Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
       
    	return "view:/user/loginView.jsp";
    }
}
