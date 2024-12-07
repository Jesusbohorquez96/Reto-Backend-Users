package com.jbohorquez.microservices_users.application.dto;

import java.time.LocalDate;

public class UserResponse {

    private Long userId;
    private String userName;
    private String userLastName;
    private Long userIdentityDocument;
    private String userPhone;
    private LocalDate userBirthdate;
    private String userEmail;
    private String userPassword;
    private String userRol;

    public UserResponse() {
    }

    public UserResponse(long l, String john, String doe, String mail, String number) {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Long getUserIdentityDocument() {
        return userIdentityDocument;
    }

    public void setUserIdentityDocument(Long userIdentityDocument) {
        this.userIdentityDocument = userIdentityDocument;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public LocalDate getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(LocalDate userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRol() {
        return userRol;
    }

    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }
}
