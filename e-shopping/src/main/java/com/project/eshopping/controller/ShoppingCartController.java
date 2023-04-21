package com.project.eshopping.controller;

import com.project.eshopping.repository.CartRepository;
import com.project.eshopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {

    @Autowired
    private CartRepository cartRepository;


    @GetMapping("/overview")
    public String overview(Model model) {
        model.addAttribute("carts", cartRepository.findAll());
        return "overview";
    }

}
