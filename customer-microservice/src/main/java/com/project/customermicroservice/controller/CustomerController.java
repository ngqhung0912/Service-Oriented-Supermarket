package com.project.customermicroservice.controller;

import com.project.customermicroservice.entity.Customer;
import com.project.customermicroservice.entity.CustomerResponse;
import com.project.customermicroservice.entity.NewCustomerInformation;
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
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer/id")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("id") String Id) {
        Customer customer = customerRepository.findCustomerById(Id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/customer/create")
    public ResponseEntity<String> createNewCustomer(@RequestBody NewCustomerInformation  newCustomer) {
        String id = newCustomer.getId();
        Customer customer = new Customer(
                newCustomer.getUsername(),
                newCustomer.getName(),
                newCustomer.getEmail(),
                newCustomer.getAddress());

        customer.setId(id);
        System.out.println(newCustomer.getId());
        customerRepository.save(customer);
       return new ResponseEntity<>("Customer created", HttpStatus.CREATED);
    }
}
