package com.project.itemcomparision.service;

import com.project.itemcomparision.entity.ProductInformation;
import com.project.itemcomparision.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ItemComparisonService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String STOCK_SERVICE_ID = "stockMicroservices";
    private static final String STOCK_SERVICE_ENDPOINT = "/stock-microservices/stock?";
    public String getProductInformationFromStock(int productId) throws ProductNotFoundException {
        try {
            ResponseEntity<ProductInformation> productInformationResponseEntity =  restTemplate.exchange("http://"+STOCK_SERVICE_ID+STOCK_SERVICE_ENDPOINT+"id="+productId,
                    HttpMethod.GET,
                    null,
                    ProductInformation.class);
            ProductInformation productInformation = productInformationResponseEntity.getBody();
            return "Barcode: " + productInformation.getProductId() + "\n" +
                    "Name: " + productInformation.getName() + "\n" +
                    "Description: " + productInformation.getDescription() + "\n" +
                    "Price: " + productInformation.getPrice();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw e;
            }
            throw new ProductNotFoundException("This product does not exist.");
        }
    }
    public String getProductInformationFromStock(String productName) throws ProductNotFoundException {
        try {
            ResponseEntity<ProductInformation> productInformationResponseEntity =  restTemplate.exchange("http://"+STOCK_SERVICE_ID+STOCK_SERVICE_ENDPOINT+"name="+productName,
                    HttpMethod.GET,
                    null,
                    ProductInformation.class);
            ProductInformation productInformation = productInformationResponseEntity.getBody();
            return "Barcode: " + productInformation.getProductId() + "\n" +
                    "Name: " + productInformation.getName() + "\n" +
                    "Description: " + productInformation.getDescription() + "\n" +
                    "Price: " + productInformation.getPrice();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw e;
            }
            throw new ProductNotFoundException("This product does not exist.");
        }
    }

}
