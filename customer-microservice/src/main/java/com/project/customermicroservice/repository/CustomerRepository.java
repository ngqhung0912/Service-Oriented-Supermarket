package com.project.customermicroservice.repository;

import com.project.customermicroservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer,Long>{
    List<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer findCustomerByUsername(String username);
    Customer findCustomerByEmail(String email);
}

