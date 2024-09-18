package com.scm.scm20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class userController {

    @GetMapping("/dashboard")
    public String dashboard(){
        return "/user/dashboard";
    }

    @GetMapping("/profile")
    public String profile(){
        return "/user/profile";
    }

}
