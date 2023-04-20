package com.example.security_upgrade_training.controllers;


import com.example.security_upgrade_training.models.User;
import com.example.security_upgrade_training.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;   //putted in constructor by @RequiredArgsConstructor

    @GetMapping("/login")                    //login page address, showed it in configuration
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user) {
        System.out.println("User:" + user);
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl() {               //page for authenticated users
        return "hello";
    }


}