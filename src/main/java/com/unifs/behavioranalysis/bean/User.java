package com.unifs.behavioranalysis.bean;

public class User {
    private String userId;

    private String name;

    private String userNum;

    private String userName;

    private String userEmail;

    private String departmentId;

    private String state;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }


    @Override
    public String toString()
    {
        return "User{" + "userId='" + userId + '\'' + ", name='" + name + '\'' + ", userNum='" + userNum + '\'' + ", " +
                "userName='" + userName + '\'' + ", userEmail='" + userEmail + '\'' + ", departmentId='" +
                departmentId + '\'' + ", state='" + state + '\'' + '}';
    }
}