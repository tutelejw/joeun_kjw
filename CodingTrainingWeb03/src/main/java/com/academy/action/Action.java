// src/main/java/com/academy/action/Action.java
package com.academy.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}