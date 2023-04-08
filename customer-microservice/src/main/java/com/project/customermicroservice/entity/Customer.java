package com.project.customermicroservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Customer {
    @GeneratedValue
    @Id
    @Getter
    private long id;

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String address;

    public Customer (String username, String name, String email, String address) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.name = name;
    }

}
