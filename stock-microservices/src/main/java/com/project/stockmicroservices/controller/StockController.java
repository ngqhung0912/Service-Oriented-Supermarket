package com.project.stockmicroservices.controller;

import com.project.stockmicroservices.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StockController {
    @Autowired
    StockRepository stockRepository;

    @GetMapping("/overview")
    public String stockOverview(Model model) {
        model.addAttribute("products", stockRepository.findAll());
        return "overview";
    }
}
