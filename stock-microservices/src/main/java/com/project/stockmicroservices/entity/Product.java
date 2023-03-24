package com.project.stockmicroservices.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import  jakarta.persistence.Entity;
import  jakarta.persistence.GeneratedValue;
import  jakarta.persistence.GenerationType;
import  jakarta.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    private int productId;
    @Getter
    @Setter

    private String name;
    @Getter
    @Setter

    private String description;
    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private int count;


}
