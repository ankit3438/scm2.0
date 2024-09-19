package com.scm.scm20.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.scm.scm20.entities.User;
import com.scm.scm20.forms.UserForm;
import com.scm.scm20.helpers.Message;
import com.scm.scm20.helpers.MessageType;
import com.scm.scm20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
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
        //userForm.setName("ankit jha");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        //fetch form date
        //User form me store karwayenge
        //validate karenge
        //save to database
        //message ="registration successfull"
        System.out.println(userForm.toString());

        if(rBindingResult.hasErrors())return "register";

        //userForm -> user banaya h kyu ki userform me pura data nahi tha

        //User user=User.builder()
        //.name(userForm.getName())
        //.email(userForm.getEmail())
        //.password(userForm.getPassword())
        //.about(userForm.getAbout())
        //.phoneNumber(userForm.getPhoneNumber())
        //.profilePic("C:\\Users\\ankit\\Downloads\telephone")
        //.build();

        User user =new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("C:\\Users\\ankit\\Downloads\telephone");


        User savadUser=userService.saveUser(user);

        Message message=Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message",message);

        System.out.println("user saved");
        //redirect to register page
        return "redirect:/register";
    }
    
}
