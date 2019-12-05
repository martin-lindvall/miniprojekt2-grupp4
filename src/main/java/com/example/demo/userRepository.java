package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class userRepository {

    @Autowired
    DataSource dataSource;

    private List<UserInfo> users;
    private List<Integer> userLowScore = new ArrayList<>();

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

    public void setUserLowScore(int count) {
        userLowScore.add(count);
    }

    public List<Integer> getUserLowScore() {
        return userLowScore;
    }

    public boolean testDB() throws SQLException {
        int two = 0;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1+1")) {
            rs.next();
            two = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return two == 2;
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