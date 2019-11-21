package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    private userRepository repository;

    @GetMapping("/signup")
    public String signup(){
    return "signup";
}
    @PostMapping("/signup")
    public String signupPost(HttpSession session, @RequestParam(required = false) String username, @RequestParam(required = false)  String password, @RequestParam(required = false)  String mail){
        UserInfo userInfo = new UserInfo(username,password,mail);
        repository.saveUser(userInfo);

        List<String> users = (List<String>)session.getAttribute("users");
//        if(users == null) {
//        users = new ArrayList<>();
//        }
//            //session.setAttribute("username", username);
//        users.add(username);
//        users.add(password);
//        users.add(mail);

        session.setAttribute("users",users);

//        if(username.equals()  == null){
//            return "signup";
//        }

        return "login";
    }

    @GetMapping("/login")
    public String login(){
    return "login";
    }
    @PostMapping("/login")
    public String loginPost(HttpSession session, @RequestParam String username, @RequestParam String password){


        
        
        session.setAttribute("username", username);
        return "gametest";
    }

}
