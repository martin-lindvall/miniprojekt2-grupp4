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

    @GetMapping("/")
    public String redirect(){
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signup(HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("userkey");

        if (user != null && user.getLoggedIn()) {
            return "redirect:/grid";
        } else {

            return "signup";
        }

    }

    @PostMapping("/signup")
    public String signupPost(HttpSession session, @RequestParam(required = false) String username, @RequestParam(required = false) String password, @RequestParam(required = false) String mail) {
        UserInfo userInfo = new UserInfo(username, password, mail, false);
        repository.saveUser(userInfo);

//        List<String> users = (List<String>)session.getAttribute("users");
//        session.setAttribute("users",users);

//        }

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
//       UserInfo userInfo = repository.getUser();
        UserInfo user = repository.checkLogin(username, password);
        if (user.getLoggedIn()) {
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

//    @GetMapping("/grid")
//    public String game(HttpSession session) {
//        UserInfo user = (UserInfo) session.getAttribute("userkey");
//
//        if (user != null && user.getLoggedIn()) {
//            return "gameGrid";
//        } else {
//
//            return "login";
//        }
//    }
}