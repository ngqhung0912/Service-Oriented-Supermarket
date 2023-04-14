package com.project.supermarketapigateway.service;

import com.project.supermarketapigateway.dto.NewUserDataDTO;
import com.project.supermarketapigateway.dto.UserDataDTO;
import com.project.supermarketapigateway.exception.CannotCreateUserException;
import com.project.supermarketapigateway.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerWelcomeService {
    @Autowired
    private RestTemplate restTemplate;

//    private static final String KUBERNETES_CUSTOMER_SERVICE_ID = "customer-kubernetes-service.supermarket:98/";
    private static final String KUBERNETES_CUSTOMER_SERVICE_ID = "localhost:8098/";

    private static final String CONSUL_CUSTOMER_SERVICE_ID = "customerMicroservice";
    private static final String CUSTOMER_INFORMATION_RETRIEVAL_ENDPOINT = "/customer-microservice/customer/email?";
    private static final String CREATE_CUSTOMER_ENDPOINT = "/customer-microservice/customer/create";

    public String getCustomerInformation(String email) throws UserNotFoundException {
        try {
// The below commented code is for Consul
//            ResponseEntity<UserDataDTO> userDataResponseEntity = restTemplate.exchange("http://" + CONSUL_CUSTOMER_SERVICE_ID + CUSTOMER_INFORMATION_RETRIEVAL_ENDPOINT + "email=" + email,
//                    HttpMethod.GET,
//                    null,
//                    UserDataDTO.class);
            ResponseEntity<UserDataDTO> userDataResponseEntity = restTemplate.exchange("http://" + KUBERNETES_CUSTOMER_SERVICE_ID + CUSTOMER_INFORMATION_RETRIEVAL_ENDPOINT + "email=" + email,
                    HttpMethod.GET,
                    null,
                    UserDataDTO.class);

            UserDataDTO userData = userDataResponseEntity.getBody();
            assert userData != null;
            return "Authentication succeeded! Your credentials: " + userData.getEmail() + ", " + userData.getName();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw e;
            }
            throw new UserNotFoundException("this account does not exist");
        }
    }

    public void createNewCustomer(NewUserDataDTO newUserData) {
        try {
            restTemplate.postForObject("http://" + KUBERNETES_CUSTOMER_SERVICE_ID + CREATE_CUSTOMER_ENDPOINT, newUserData, String.class);
            // The below commented code is for Consul
            //            restTemplate.postForObject("http://" + CONSUL_CUSTOMER_SERVICE_ID + CREATE_CUSTOMER_ENDPOINT, newUserData, String.class);
        } catch (HttpClientErrorException e) {
            throw new CannotCreateUserException(e.toString());
        }
    }

    public String test(String test) {
        return test;
    }

}
