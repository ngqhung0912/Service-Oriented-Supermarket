package com.project.stockmicroservices.controller;

import com.project.stockmicroservices.entity.StockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.stockmicroservices.repository.StockRepository;

/**
 * @author Hung Nguyen
 **/

@RestController
public class StockController {
    @Autowired StockRepository stockRepository;


    @GetMapping("/current-stock")
    public StockResponse getCurrentStock(){
        return new StockResponse(stockRepository.findAll());
    }
}
