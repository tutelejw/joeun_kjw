// src/main/java/com/academy/action/GalleryAction.java
package com.academy.action;

import com.academy.dao.PhotoDAO;
import com.academy.vo.UserVO;
import com.academy.vo.PhotoVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.apache.log4j.Logger;

public class GalleryAction implements Action {
    private static final Logger logger = Logger.getLogger(GalleryAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserVO user = (UserVO) session.getAttribute("loggedInUser");
        String userId = (user != null) ? user.getUserId() : null;

        PhotoDAO photoDAO = new PhotoDAO();
        List<PhotoVO> photoList = photoDAO.getPhotos(userId);

        request.setAttribute("photoList", photoList);
        logger.info("갤러리 목록 조회 성공. 항목 수: " + photoList.size());
        return "/jsp/gallery.jsp";
    }
}