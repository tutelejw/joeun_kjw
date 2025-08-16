package com.academy.vo;

public class UserVO {
    private String userId;
    private String userPassword;
    private String userName;
    private int userAge;
    private String userAddress;
    private String memoryTitle;

    // Getter and Setter
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public int getUserAge() { return userAge; }
    public void setUserAge(int userAge) { this.userAge = userAge; }
    public String getUserAddress() { return userAddress; }
    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }
    public String getMemoryTitle() { return memoryTitle; }
    public void setMemoryTitle(String memoryTitle) { this.memoryTitle = memoryTitle; }
}