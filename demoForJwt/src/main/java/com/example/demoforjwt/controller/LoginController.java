package com.example.demoforjwt.controller;

import com.example.demoforjwt.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {
    @GetMapping("/")
    public String initMethod(Model model){
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @ModelAttribute("user")@Valid User user,
                        Model model){

        if(user.getName().equals("zsj") && user.getPwd().equals("123")){
            return "forward:/"+"success";
        }else {
            model.addAttribute("exception", "wrong username or pwd");
            model.addAttribute("url", request.getRequestURL());
            return "errorPage";
        }
    }


}
