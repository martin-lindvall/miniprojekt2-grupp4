package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userRepository {
    private List<UserInfo> users;

    public userRepository() {
        users = new ArrayList<>();

    }

    public void saveUser(UserInfo userInfo){
        users.add(userInfo);

    }

public UserInfo checkLogin (String name, String password) {
        for (UserInfo uInfo : users) {
            if (uInfo.getUserName().equalsIgnoreCase(name) && uInfo.getPassword().equalsIgnoreCase(password)) {
               uInfo.setLoggedIn(true);
                return uInfo;
            }
        }

        return null;
}

//    public UserInfo getUserInfo (String userName, String password){
//    for (UserInfo users : userInfo) {
//if(users.getUserName() == userName && users.getPassword() == password){
//
//    return users;
//}
//
//    }
//    return null;
//    }


}