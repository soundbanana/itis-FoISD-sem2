package com.chemaev.controller;

import com.chemaev.acpects.Loggable;
import com.chemaev.dto.CreateUserRequestDto;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
