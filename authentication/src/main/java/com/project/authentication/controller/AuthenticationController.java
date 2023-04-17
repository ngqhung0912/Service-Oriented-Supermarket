package com.project.authentication.controller;

import com.project.authentication.entity.NewUserData;
import com.project.authentication.exception.UserNotFoundException;
import com.project.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/")
    public ResponseEntity<String> test(@RequestParam("name") String name) {
//        return ResponseEntity.ok("Hello!" + authenticatedUserDTO.getName());
        return ResponseEntity.ok("Hello, " + name + "!");

    }



//    @GetMapping("/")
//    public String authenticate(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
//        Map<String, Object> loginAttributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
////        System.out.println("haizzz" +oAuth2AuthenticationToken.getPrincipal().toString());
//        String email = loginAttributes.get("email").toString();
//        String name  = loginAttributes.get("name").toString();
//        try {
//            String customerInformation = authenticationService.getCustomerInformation(email);
//            model.addAttribute("UserData", customerInformation);
//            return "authentication-complete";
//        } catch (UserNotFoundException e) {
//            NewUserData newUserData = new NewUserData();
//            newUserData.setEmail(email);
//            newUserData.setName(name);
//            model.addAttribute("UserData", newUserData);
////            model.addAttribute("CustomerName", name);
//            return "create-new-user";
//        }
//    }
//
//    @PostMapping("/create-user")
//    public String createNewUser(Model model, @ModelAttribute NewUserData newUserData) {
//        System.out.println("create user " + newUserData.toString());
//        authenticationService.createNewCustomer(newUserData);
//        return "new-user-created";
//    }




}
