package com.project.customermicroservice.controller;

import com.project.customermicroservice.entity.Customer;
import com.project.customermicroservice.entity.CustomerResponse;
import com.project.customermicroservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/all-customers")
    public CustomerResponse getCustomerList() { return new CustomerResponse(customerRepository.findAll()); }

    @GetMapping("/customer/email")
    public ResponseEntity<Customer> getCustomerByEmail(@RequestParam("email") String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }


    // TODO Request processing failed: org.springframework.orm.jpa.JpaSystemException: ids for this class must be
    //  manually assigned before calling save(): com.project.customermicroservice.entity.Customer] with root cause
    //  org.hibernate.id.IdentifierGenerationException: ids for this class must be manually assigned before calling save():
    @PostMapping("/customer/create")
    public ResponseEntity<String> createNewCustomer(@RequestBody Customer customer) {
        // TODO If I set the ID manually like this, then it works.
//        customer.setId(10L);
        customerRepository.save(customer);
       return new ResponseEntity<>("Customer created", HttpStatus.CREATED);
    }
}
