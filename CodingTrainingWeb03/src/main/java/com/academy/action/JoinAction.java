// src/main/java/com/academy/action/JoinAction.java
package com.academy.action;

import com.academy.dao.UserDAO;
import com.academy.vo.UserVO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.log4j.Logger;

public class JoinAction implements Action {
    private static final Logger logger = Logger.getLogger(JoinAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        int userAge = Integer.parseInt(request.getParameter("userAge"));
        String userAddress = request.getParameter("userAddress");
        String memoryTitle = request.getParameter("memoryTitle");
        System.out.println("=======JoinAction - " + userId + " /" + userPassword+ " /" + userName + " /" + userAge + " /" + userAddress +"/" + memoryTitle);
        UserDAO userDAO = new UserDAO();

        if (userDAO.checkUserId(userId)) {
            request.setAttribute("message", "이미 존재하는 아이디입니다.");
            return "/jsp/joinForm.jsp";
        }

        String hashedPassword = null;
        try {
            hashedPassword = hashPassword(userPassword);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error("패스워드 해싱 오류: " + e.getMessage());
            request.setAttribute("message", "패스워드 처리 중 오류가 발생했습니다.");
            return "/jsp/joinForm.jsp";
        }
        
        UserVO newUser = new UserVO();
        newUser.setUserId(userId);
        newUser.setUserPassword(hashedPassword);
        newUser.setUserName(userName);
        newUser.setUserAge(userAge);
        newUser.setUserAddress(userAddress);
        newUser.setMemoryTitle(memoryTitle);
        
        userDAO.insertUser(newUser);

        request.setAttribute("message", "회원가입이 완료되었습니다!");
        return "/jsp/login.jsp";
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 15;
        byte[] salt = new byte[16];
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
}