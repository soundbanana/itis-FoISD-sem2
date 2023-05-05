package com.chemaev.controller;

import com.chemaev.acpect.Loggable;
import com.chemaev.dto.CreateUserRequestDto;
import com.chemaev.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class MainController {

    private final WeatherService weatherService = new WeatherService();

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
    @GetMapping("/weather")
    public String getWeather(@RequestParam Optional<String> city) {
        return weatherService.getWeather(city.orElse("Moscow"));
    }
}
