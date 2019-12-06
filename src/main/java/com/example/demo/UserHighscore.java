package com.example.demo;

public class UserHighscore {

    private int score;
    private String userName;

    public UserHighscore(int score, String userName) {
        this.score = score;
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
