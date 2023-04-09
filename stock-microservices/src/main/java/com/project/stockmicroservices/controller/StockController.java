package com.project.stockmicroservices.controller;

import com.project.stockmicroservices.entity.Product;
import com.project.stockmicroservices.entity.ProductStockUpdate;
import com.project.stockmicroservices.entity.ProductUpdateResponse;
import com.project.stockmicroservices.entity.StockResponse;
import com.project.stockmicroservices.service.StockService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.stockmicroservices.repository.StockRepository;

import java.util.List;
import java.util.Optional;


/**
 * @author Hung Nguyen
 **/

@Controller
public class StockController {
    @Autowired StockRepository stockRepository;
    @Autowired
    StockService stockService;


    @GetMapping("/current-stock")
    public StockResponse getCurrentStock(){
        return new StockResponse(stockRepository.findAll());
    }

    @GetMapping("/stock")
    public ResponseEntity<Product> getProductById(@RequestParam("productId") Long id) {
        // return product info in JSON
        Optional<Product> products = stockRepository.findById(id);
        return products.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/stock")
    public ResponseEntity<Product> getProductByName(@RequestParam("name") String name) {
        // return product info in JSON
        Optional<Product> products = stockRepository.findByName(name);
        return products.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/get-products")
    public ResponseEntity<List<Product>> getProductsById(@RequestParam("ids") String ids) {
        List<Long> idList = Arrays.stream(ids.split(",")).toList()
            .stream().map(Long::parseLong).toList();
        return new ResponseEntity<>(stockRepository.findByProductIdIn(idList), HttpStatus.OK);
    }

    @PostMapping("/update-stock")
    public ResponseEntity<ProductUpdateResponse> updateProduct(@RequestBody ProductStockUpdate stockUpdate) {
        return new ResponseEntity<>(stockService.updateProductInfo(stockUpdate), HttpStatus.OK);
    }

    @GetMapping("/overview")
    public String stockOverview(Model model) {
        model.addAttribute("products", stockRepository.findAll());
        return "overview";
    }
}
