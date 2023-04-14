package com.project.authentication.controller;

//
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.gson.GsonFactory;
//import com.project.authentication.dto.ClaimsDTO;
//import com.project.authentication.entity.NewUserData;
//import com.project.authentication.exception.UserNotFoundException;
//import com.project.authentication.service.AuthenticationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.oidc.OidcIdToken;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RestController
public class AuthenticationRestController {
//    @Autowired
//    private AuthenticationService authenticationService;
//
//
//    private static final String ERROR_PATH = "/error";
//
//    @GetMapping(path = ERROR_PATH)
//    public String handleError() {
//        return "WHOOPS";
//    }
//
////    private void verifyGoogleToken() {
////        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
////                // Specify the CLIENT_ID of the app that accesses the backend:
////                .setAudience(Collections.singletonList(CLIENT_ID))
////                // Or, if multiple clients access the backend:
////                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
////                .build();
////// (Receive idTokenString by HTTPS POST)
////
////        GoogleIdToken idToken = verifier.verify();
////        if (idToken != null) {
////            Payload payload = idToken.getPayload();
////
////            // Print user identifier
////            String userId = payload.getSubject();
////            System.out.println("User ID: " + userId);
////
////            // Get profile information from payload
////            String email = payload.getEmail();
////            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
////            String name = (String) payload.get("name");
////            String pictureUrl = (String) payload.get("picture");
////            String locale = (String) payload.get("locale");
////            String familyName = (String) payload.get("family_name");
////            String givenName = (String) payload.get("given_name");
////
////            // Use or store profile information
////            // ...
////
////        } else {
////            System.out.println("Invalid ID token.");
////        }
////
////    }
//
//    @GetMapping("/authenticate")
//    public String authenticate (OAuth2AuthenticationToken oAuth2AuthenticationToken) {
//        Map<String, Object> loginAttributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
////        String email = loginAttributes.get("email").toString();
////        String name  = loginAttributes.get("name").toString();
//        return "..";
//
//
////        try {
////            String customerInformation = authenticationService.getCustomerInformation(email);
////            model.addAttribute("UserData", customerInformation);
////            return "authentication-complete";
////        } catch (UserNotFoundException e) {
////            NewUserData newUserData = new NewUserData();
////            newUserData.setEmail(email);
////            newUserData.setName(name);
////            model.addAttribute("UserData", newUserData);
//////            model.addAttribute("CustomerName", name);
////            return "create-new-user";
////        }
//    }
//
//    @PostMapping("/create-user")
//    public String createNewUser(Model model, @ModelAttribute NewUserData newUserData) {
//        System.out.println("create user " + newUserData.toString());
//        authenticationService.createNewCustomer(newUserData);
//        return "new-user-created";
//    }
//
////    @PostMapping("/token")
////    public String getToken(@RequestBody ClaimsDTO claimsDTO){
////        Map<String, Object> claims = new HashMap<>();
////        claims.put("nonce", claimsDTO.getNonce());
////        claims.put("at_hash", claimsDTO.getAt_hash());
////        claims.put("sub", claimsDTO.getSub());
////        return authenticationService.generateToken(claims);
////    }
////
////    @GetMapping("/validate-token")
////    public String validateToken(@RequestParam("token") String token){
////         authenticationService.validateToken(token);
////         return "token-validated";
////    }
}
