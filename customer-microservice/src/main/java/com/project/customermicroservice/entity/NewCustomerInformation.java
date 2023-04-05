package com.project.customermicroservice.entity;

import lombok.Getter;
import lombok.Setter;

public class NewCustomerInformation {
    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    int age;

    @Getter
    @Setter
    String address;

    @Getter
    @Setter
    String username;

    public String toString() {
        String space = " ";
        return "NewCustomer info: " +this.email + space +
                this.username + space +
                this.name + space +
                this.age + space +
                this.address;
    }

}
