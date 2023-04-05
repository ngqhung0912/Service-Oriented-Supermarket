package com.project.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.authentication.entity.NewUserData;
import com.project.authentication.entity.UserData;
import com.project.authentication.exception.UserNotFoundException;
import com.project.authentication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    private static final String ERROR_PATH = "/error";

    @GetMapping(path = ERROR_PATH)
    public String handleError() {
        return "WHOOPS";
    }

    @GetMapping("/")
    public String authenticate(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> loginAttributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
        String email = loginAttributes.get("email").toString();
        String name  = loginAttributes.get("name").toString();
        try {
            String customerInformation = authenticationService.getCustomerInformation(email);
            model.addAttribute("UserData", customerInformation);
            return "authentication-complete";
        } catch (UserNotFoundException e) {
            NewUserData newUserData = new NewUserData();
            newUserData.setEmail(email);
            newUserData.setName(name);
            model.addAttribute("UserData", newUserData);
//            model.addAttribute("CustomerName", name);
            return "create-new-user";
        }
    }

    @PostMapping("/create-user")
    public String createNewUser(Model model, @ModelAttribute NewUserData newUserData) {
        System.out.println("create user " + newUserData.toString());
        authenticationService.createNewCustomer(newUserData);
        return "new-user-created";
    }


}
