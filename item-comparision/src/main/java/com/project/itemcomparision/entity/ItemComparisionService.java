package com.project.itemcomparision.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ItemComparisionService {
    @Autowired private RestTemplate restTemplate;

    private static final String STOCK_SERVICE_ID = "stockMicroservices";
    private static final String STOCK_SERVICE_ENDPOINT = "/stock-microservices/stock?";
    public ResponseEntity<ProductInformation> getProductInformationFromStock(int productId) {
        return restTemplate.exchange("http://"+STOCK_SERVICE_ID+STOCK_SERVICE_ENDPOINT+"id="+productId,
                HttpMethod.GET,
                null,
                ProductInformation.class);
    }
}
