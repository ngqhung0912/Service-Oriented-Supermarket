package com.project.barcodereader.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ProductInformation {
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

}
