package com.example.demo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserInfo {

@Size(min=1, max=20)
private String userName;
@Size(min=6, max=20)
private String password;

@Email
@NotBlank
private String mail;
private Boolean isLoggedIn;
private int id;

public UserInfo() {
}

public UserInfo(String userName, String password, String mail, Boolean isLoggedIn, int id) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.isLoggedIn = isLoggedIn;
        this.id = id;
}

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


}
