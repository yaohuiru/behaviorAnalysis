package com.unifs.behavioranalysis.bean;

import java.util.UUID;
import lombok.Data;

@Data
public class User {
    private String userId;

    private String name;

    private String userNum;

    private String userName;

    private String userEmail;

    private String departmentId;

    private String state;

    public User() {
    }

    public User(String name, String userNum, String userName, String userEmail, String departmentId, String state) {
        this.name = name;
        this.userNum = userNum;
        this.userName = userName;
        this.userEmail = userEmail;
        this.departmentId = departmentId;
        this.state = state;
    }

    private String password;


}