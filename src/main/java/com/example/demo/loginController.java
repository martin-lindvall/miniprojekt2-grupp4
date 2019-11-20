package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class loginController {

@GetMapping("/signup")
    public String signup(){
    return "signup";
}
    @PostMapping("/signup")
    public String signupPost(HttpSession session, @RequestParam(required = false) String username, @RequestParam(required = false)  String password, @RequestParam(required = false)  String mail){
        session.setAttribute("username", username);
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
