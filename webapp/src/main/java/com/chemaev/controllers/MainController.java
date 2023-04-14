package com.chemaev.controllers;

import com.chemaev.aspect.Loggable;
import com.chemaev.dto.university.CreateUserRequestDto;
import com.chemaev.model.university.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/home")
    @Loggable
    public String home(HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        return "home";
    }

    @GetMapping("/")
    @Loggable
    public String index() {
        return "index";
    }

    @GetMapping("/sign_up")
    public String signUp(Model model) {
        model.addAttribute("user", new CreateUserRequestDto());
        return "sign_up";
    }

    @GetMapping("/user_profile")
    public String userProfile(Model model, HttpServletRequest httpServletRequest) {
        String currentPrincipalName = httpServletRequest.getUserPrincipal().getName();
        model.addAttribute("user", currentPrincipalName);
        return "user_profile";
    }
}