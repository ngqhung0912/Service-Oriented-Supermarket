package com.project.stockmicroservices.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class Product {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Getter
    @Setter
    private Long productId;
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
