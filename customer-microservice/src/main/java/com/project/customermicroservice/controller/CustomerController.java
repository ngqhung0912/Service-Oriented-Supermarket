package com.project.customermicroservice.controller;

import com.project.customermicroservice.entity.Customer;
import com.project.customermicroservice.entity.CustomerResponse;
import com.project.customermicroservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/all-customers")
    public CustomerResponse getCustomerList() { return new CustomerResponse(customerRepository.findAll()); }

    @GetMapping("/customer/email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam("email") String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        return getCustomerResponseEntity(customer);
    }

    private ResponseEntity<Customer> getCustomerResponseEntity(Customer customer) {
        if (customer != null) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
              return ResponseEntity.notFound().build();
        }
    }
}
