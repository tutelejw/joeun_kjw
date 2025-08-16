// src/main/java/com/academy/vo/PhotoVO.java
package com.academy.vo;

import java.util.Date;

public class PhotoVO {
    private int photoId;
    private String photoPath;
    private String photoDescription;
    private Date photoDate;
    private String userId;
    private String isPublic;

    // Getter and Setter
    public int getPhotoId() { return photoId; }
    public void setPhotoId(int photoId) { this.photoId = photoId; }
    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
    public String getPhotoDescription() { return photoDescription; }
    public void setPhotoDescription(String photoDescription) { this.photoDescription = photoDescription; }
    public Date getPhotoDate() { return photoDate; }
    public void setPhotoDate(Date photoDate) { this.photoDate = photoDate; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getIsPublic() { return isPublic; }
    public void setIsPublic(String isPublic) { this.isPublic = isPublic; }
}