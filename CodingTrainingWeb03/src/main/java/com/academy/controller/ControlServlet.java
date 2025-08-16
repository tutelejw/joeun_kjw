
// src/main/java/com/academy/controller/ControlServlet.java
package com.academy.controller;

import com.academy.action.Action;
import com.academy.action.GalleryAction;
import com.academy.action.JoinAction;
import com.academy.action.LoginAction;
import com.academy.action.PhotoUploadAction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

@WebServlet("*.do")
public class ControlServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ControlServlet.class);
    private Map<String, Action> actionMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        // .do 요청에 대한 Action 클래스 매핑
        actionMap.put("/join.do", new JoinAction());
        actionMap.put("/login.do", new LoginAction());
        actionMap.put("/gallery.do", new GalleryAction());
        actionMap.put("/upload.do", new PhotoUploadAction());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getRequestURI().substring(request.getContextPath().length());
        String view = null;
        // 요청 URI에서 action 추출 (/UserProject/registerForm.do 등)
    	String uri = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String actionname = uri.substring(contextPath.length());
    	System.out.println("ControlServlet - processRequest 인입됨");
    	System.out.println("ControlServlet - processRequest 인입됨 : uri : " + uri);
    	System.out.println("ControlServlet - processRequest 인입됨 : contextPath : " + contextPath);
    	System.out.println("ControlServlet - processRequest 인입됨 : action : " + actionname);
    	System.out.println("ControlServlet - processRequest 인입됨 : command : " + command);

        if (command.equals("/joinForm.do")) {
        	System.out.println("if문내부 1 /joinForm.do - /jsp/joinForm.jsp");
        	view = "/jsp/joinForm.jsp";
        } else if (command.equals("/main.do")) {
        	System.out.println("if문내부 2 /main.do - /jsp/main.jsp");
        	view = "/jsp/main.jsp";
        } else if (command.equals("/login.do")) {
        	System.out.println("if문내부 3 /login.do - /jsp/login.jsp");
            view = "/jsp/login.jsp";
        } else if (command.equals("/gallery.do")) {
        	System.out.println("if문내부 4 /gallery.do");
            Action action = actionMap.get(command);
            view = action.execute(request, response);
        } else if (command.equals("/upload.do")) {
        	System.out.println("if문내부 5 /upload.do - /jsp/photoUpload.jsp");
            view = "/jsp/photoUpload.jsp";
        } else if (command.equals("/logout.do")) {
        	System.out.println("if문내부 6 /logout.do - redirect:login.do");
            request.getSession().invalidate();
            view = "redirect:login.do";
        } else {
        	System.out.println("if문내부 7 else - /error/404.jsp");
            view = "/error/404.jsp";
        }

        dispatch(request, response, view);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getRequestURI().substring(request.getContextPath().length());
        Action action = actionMap.get(command);
        String view = null;

        if (action != null) {
            view = action.execute(request, response);
        } else {
            view = "/error/404.jsp";
        }

        dispatch(request, response, view);
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
        if (view.startsWith("redirect:")) {
            response.sendRedirect(request.getContextPath() + view.substring("redirect:".length()));
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            dispatcher.forward(request, response);
        }
    }
}