package com.example.demo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserInfo {

@Size(min=1, max=20)
private String userName;
@Size(min=6, max=20)
private String password;

@Email
@NotBlank
private String mail;
private Boolean isLoggedIn;
private int lowScore;

public UserInfo() {
}

public UserInfo(String userName, String password, String mail, Boolean isLoggedIn) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.isLoggedIn = isLoggedIn;
        this.lowScore = 0;
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

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getLowScore() {
        int ret = this.lowScore;
        userRepository rep = new userRepository();
        for (int i : rep.getUserLowScore()) {
            if (i < ret) {
                ret = i;
            }
        }
        return ret;
    }

    public void setLowScore(int lowScore) {
        int ret = lowScore;
        userRepository low = new userRepository();
        for (int i : low.getUserLowScore()) {
            if (i < ret) {
                ret = i;
            }
        }
        this.lowScore = ret;
    }
    public List<Integer> getLowScoreList() {
    userRepository rep = new userRepository();
    List<Integer> ret = rep.getUserLowScore();
    return ret;
    }
}
