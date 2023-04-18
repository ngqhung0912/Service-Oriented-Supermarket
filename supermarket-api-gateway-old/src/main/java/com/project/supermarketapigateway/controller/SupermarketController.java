package com.project.supermarketapigateway.controller;

import com.project.supermarketapigateway.dto.NewUserDataDTO;
import com.project.supermarketapigateway.exception.UserNotFoundException;
import com.project.supermarketapigateway.service.CustomerWelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class SupermarketController {
    @Autowired
    CustomerWelcomeService customerWelcomeService;

    @GetMapping("/")
    public String authenticate(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> loginAttributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
        System.out.println("haizz " + oAuth2AuthenticationToken.getName());
        String email = loginAttributes.get("email").toString();
        String name  = loginAttributes.get("name").toString();
        try {
            String customerInformation = customerWelcomeService.getCustomerInformation(email);
            model.addAttribute("UserData", customerInformation);
            return "authentication-complete";
        } catch (UserNotFoundException e) {
            NewUserDataDTO newUserData = new NewUserDataDTO();
            newUserData.setEmail(email);
            newUserData.setName(name);
            model.addAttribute("UserData", newUserData);
//            model.addAttribute("CustomerName", name);
            return "create-new-user";
        }
    }

    @PostMapping("/create-user")
    public String createNewUser(Model model, @ModelAttribute NewUserDataDTO newUserData) {
        System.out.println("create user " + newUserData.toString());
        customerWelcomeService.createNewCustomer(newUserData);
        return "new-user-created";
    }






}
