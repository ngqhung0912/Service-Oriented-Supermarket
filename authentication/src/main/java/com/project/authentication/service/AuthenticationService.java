package com.project.authentication.service;

import com.project.authentication.entity.UserData;
import com.project.authentication.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
@Service

public class AuthenticationService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String CUSTOMER_SERVICE_ID = "customerMicroservice";
    private static final String CUSTOMER_SERVICE_ENDPOINT = "/customer-microservice/customer/email?";

// TODO See BarcodeReaderMicroservice
    public String getCustomerInformation(String email) throws UserNotFoundException {
        try {
            ResponseEntity<UserData> userDataResponseEntity = restTemplate.exchange("http://" + CUSTOMER_SERVICE_ID + CUSTOMER_SERVICE_ENDPOINT + "email=" + email,
                    HttpMethod.GET,
                    null,
                    UserData.class);
            UserData userData = userDataResponseEntity.getBody();
            assert userData != null;
            return "Authentication succeeded! Your credentials: " + userData.getEmail() + ", " + userData.getName();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw e;
            }
            throw new UserNotFoundException("this account does not exist");
        }
    }
}
