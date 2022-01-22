package com.example.hrinterface.controller;

import com.example.hrinterface.entity.Person;
import com.example.hrinterface.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class TestController {
    @Autowired
    ProfileService profileService;

    @GetMapping("/reg")
    public String regPerson(Model model, @RequestParam(name = "id") Integer id, @RequestParam(name = "email") String email){
        model.addAttribute("person", new Person());
        model.addAttribute("id", id);
        model.addAttribute("email", email);
        return "create.jsp";
    }

    @PostMapping("/createPerson")
    public String createPerson(HttpServletRequest req, Model model, @ModelAttribute("person") @Valid Person person,
                             BindingResult result){
        if (result.hasErrors()) {
            return "create.jsp";
        }
        try {
            int newID = profileService.createPerson(person);
            return "success"; //这里应该是redirect到下一步，类似于 return "redirect:" + url of nextpage + "?newID="+ newID
        } catch (Exception e) {
            return e.toString();
        }
    }
}
