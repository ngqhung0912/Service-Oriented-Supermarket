package com.project.supermarketapigateway.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class SupermarketController {
//    private static final String KUBERNETES_CUSTOMER_SERVICE_ID = "customer-kubernetes-service.supermarket:98/";
//    private static final String KUBERNETES_AUTHENTICATION_SERVICE_ID = "authentication-kubernetes-service.supermarket:97/";
//
    @GetMapping(path = "/")
    public ResponseEntity<String> test(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> loginAttributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
        String name  = loginAttributes.get("name").toString();
        return ResponseEntity.ok("Hello!" + name);
    }




}
