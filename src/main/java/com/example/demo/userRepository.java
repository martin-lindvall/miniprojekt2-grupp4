package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userRepository {
    private List<UserInfo> userInfo;

    public userRepository() {
        userInfo = new ArrayList<>();

    }
public UserInfo getUserInfo (String userName, String password){
    for (UserInfo users : userInfo) {
if(users.getUserName() == userName && users.getPassword() == password){

    return users;
}

    }
    return null;
    }


}