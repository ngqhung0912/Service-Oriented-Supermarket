package com.project.supermarketservice.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupermarketController {
    @GetMapping("/home")
    public String getHomePage(Model model) {
        return "home-page";
    }
}
