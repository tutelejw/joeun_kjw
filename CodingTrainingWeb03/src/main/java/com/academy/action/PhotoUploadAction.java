// src/main/java/com/academy/action/PhotoUploadAction.java
package com.academy.action;

import com.academy.dao.PhotoDAO;
import com.academy.vo.PhotoVO;
import com.academy.vo.UserVO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.log4j.Logger;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class PhotoUploadAction implements Action {
    private static final Logger logger = Logger.getLogger(PhotoUploadAction.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserVO loggedInUser = (UserVO) session.getAttribute("loggedInUser");
        
        if (loggedInUser == null) {
            request.setAttribute("message", "로그인이 필요합니다.");
            return "/jsp/login.jsp";
        }

        try {
            Part filePart = request.getPart("photoFile");
            String fileName = filePart.getSubmittedFileName();
            String description = request.getParameter("photoDescription");
            
            String uploadPath = request.getServletContext().getRealPath("/photos");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            File file = new File(uploadDir, fileName);
            filePart.write(file.getAbsolutePath());

            PhotoVO newPhoto = new PhotoVO();
            newPhoto.setPhotoPath(fileName);
            newPhoto.setPhotoDescription(description);
            newPhoto.setPhotoDate(new Date());
            newPhoto.setUserId(loggedInUser.getUserId());
            newPhoto.setIsPublic("Y"); // 기본적으로 공개

            PhotoDAO photoDAO = new PhotoDAO();
            photoDAO.insertPhoto(newPhoto);

            request.setAttribute("message", "사진이 성공적으로 업로드되었습니다.");
            logger.info("사진 업로드 성공: " + fileName + " by user " + loggedInUser.getUserId());
            return "redirect:gallery.do";

        } catch (Exception e) {
            logger.error("사진 업로드 중 오류 발생: " + e.getMessage());
            request.setAttribute("message", "사진 업로드 중 오류가 발생했습니다.");
            return "/jsp/photoUpload.jsp";
        }
    }
}
