package com.example.demo;

public class UserInfo {
private String userName;
private String password;
private String mail;

    public UserInfo(String userName, String password, String mail) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
    }

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
