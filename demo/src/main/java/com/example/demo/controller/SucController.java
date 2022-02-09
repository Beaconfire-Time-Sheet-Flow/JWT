package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rb")
public class SucController {
    @GetMapping("/suc")
    public String suc(){
        return "success";
    }
}
