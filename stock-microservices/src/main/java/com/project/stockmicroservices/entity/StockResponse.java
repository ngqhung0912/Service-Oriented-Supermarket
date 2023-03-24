package com.project.stockmicroservices.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class StockResponse {

    @Getter
    @Setter
    private List<Product> currentStock;

    public StockResponse(List<Product> currentStock) {
        this.currentStock = currentStock;
    }



}
