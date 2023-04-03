package com.project.customermicroservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CustomerResponse {
    @Getter
    @Setter
    private List<Customer> customerList;
    public CustomerResponse(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
