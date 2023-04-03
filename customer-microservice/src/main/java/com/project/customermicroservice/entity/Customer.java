package com.project.customermicroservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table
public class Customer {
    @Id
    @Getter
    private Long id;

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

}
