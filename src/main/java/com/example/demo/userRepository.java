package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class userRepository {

    @Autowired
    DataSource dataSource;

    private List<UserInfo> users;
    private List<Integer> userLowScore = new ArrayList<>();
    private UserInfo player = null;

    public userRepository() {
        users = new ArrayList<>();

    }

    public void saveUser(UserInfo userInfo) {

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Player(Username, Password, Email) VALUES(?,?,?)")) {
            ps.setString(1, userInfo.getUserName());
            ps.setString(2, userInfo.getPassword());
            ps.setString(3, userInfo.getMail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public UserInfo checkLogin (String name, String password) {

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Player WHERE Username=? AND Password=?")) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                player = new UserInfo(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        false,
                        rs.getInt("playerId"));

                System.out.println(player.getUserName() + " " + player.getPassword() + " " + player.getLoggedIn());

                if(name.equals(player.getUserName()) && password.equals(player.getPassword())) {

                    player.setLoggedIn(true);
                    System.out.println("Kom in i IF-satsen");
                }
                System.out.println(player.getUserName() + player.getLoggedIn());
            }
            return player;

        } catch (SQLException e) {
            e.printStackTrace();
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

    public void saveScoreToDB(int score) {
        System.out.println(score + " nu är vi i userRepository!");

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Points(PlayerId, Points, Kingdom) VALUES(?,?,?)")) {
            ps.setInt(1, player.getId());
            ps.setInt(2, score);
            ps.setString(3, "Animal");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Integer> getPlayerHighScoreFromDB(UserInfo player){

        List<Integer> playerHighScore = new ArrayList<>(0);

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT TOP (5) Points FROM Points INNER JOIN Player ON Points.PlayerId = Player.PlayerId WHERE Player.PlayerId = ? AND Kingdom = ? ORDER BY Points asc")){
            ps.setInt(1, player.getId());
            ps.setString(2, "Animal");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                playerHighScore.add(rs.getInt("Points"));
            }

            return playerHighScore;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return playerHighScore;

    }

    public List<UserHighscore> getGameHighScoreFromDB(){
        List<UserHighscore> gameHighScores = new ArrayList<>(0);

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT TOP (5) Points, Username FROM Points INNER JOIN Player ON Points.PlayerId = Player.PlayerId WHERE Kingdom = ? ORDER BY Points")){
            ps.setString(1, "Animal");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                gameHighScores.add(new UserHighscore(rs.getInt("Points"), rs.getString("Username")));
            }

            return gameHighScores;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return gameHighScores;
    }




}