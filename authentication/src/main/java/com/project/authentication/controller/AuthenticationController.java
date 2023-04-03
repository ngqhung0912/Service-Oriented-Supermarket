package com.project.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.authentication.entity.UserData;
import com.project.authentication.exception.UserNotFoundException;
import com.project.authentication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;



@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    private static final String ERROR_PATH = "/error";

    @GetMapping(path = ERROR_PATH)
    public String handleError() {
        return "WHOOPS";
    }


    @GetMapping("/")
    public String authenticate(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> loginAttributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
        String email = loginAttributes.get("email").toString();
        try {
            return authenticationService.getCustomerInformation(email);
        } catch (UserNotFoundException e) {
            return "This user does not exist (yet)";
        }
    }

}
