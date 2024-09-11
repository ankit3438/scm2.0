package com.scm.scm20.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.scm.scm20.forms.UserForm;

import ch.qos.logback.core.model.Model;


@Controller
public class PageController {
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
    @RequestMapping("/services")
    public String services() {
        return "services";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm= new UserForm();
        userForm.setName("ankit jha");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(){
        //fetch form date
        //User form me store karwayenge
        //validate karenge
        //save to database
        //message ="registration successfull"
        
        //redirect to register page
        return "redirect:/register";
    }
    
}
