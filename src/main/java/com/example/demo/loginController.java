package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    private userRepository repository;

    @GetMapping("/")
    public String redirect(){
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session, Model model) {
        model.addAttribute("user", new UserInfo());

        UserInfo user = (UserInfo) session.getAttribute("userkey");
        if (user != null && user.getLoggedIn()) {
            return "redirect:/grid";
        } else {

            return "signup";
        }
    }

    @PostMapping("/signup")
    public String signupPost(@Valid UserInfo user, BindingResult result, HttpSession session, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        repository.saveUser(user);


        return "login";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("userkey");

        if (user != null && user.getLoggedIn()) {
            return "redirect:/grid";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, @RequestParam String username, @RequestParam String password) {
        UserInfo user = repository.checkLogin(username, password);

        if (user != null && user.getLoggedIn()) {
            session.setAttribute("userkey", user);
            return "redirect:/grid";
        } else {
            return "login";
        }

    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

}