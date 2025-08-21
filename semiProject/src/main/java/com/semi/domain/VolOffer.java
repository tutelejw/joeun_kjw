package com.semi.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VolOffer {

    private int postId;
    private String title;
    private String authorId;
    private String phone;
    private LocalDateTime startTime; // LocalDateTime으로 변경
    private LocalDateTime endTime;   // LocalDateTime으로 변경
    private String content;
    private String region;
    private String category;
    private LocalDate offerDate;     // LocalDate로 변경
    private String processStatus;
    private LocalDateTime createdAt; // LocalDateTime으로 변경

    // --- Getters and Setters ---
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthorId() {
        return authorId;
    }
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public LocalDate getOfferDate() {
        return offerDate;
    }
    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }
    public String getProcessStatus() {
        return processStatus;
    }
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}