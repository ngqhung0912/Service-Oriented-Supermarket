package com.project.barcodereader.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BarcodeReaderServices {
    @Autowired
    private RestTemplate restTemplate;

    private static final String STOCK_SERVICE_ID = "stockMicroservices";
    private static final String STOCK_SERVICE_ENDPOINT = "/stock-microservices/stock?";


    public ResponseEntity<ProductInformation> getProductInformationFromStock(int productId) {
        return restTemplate.exchange("http://"+STOCK_SERVICE_ID+STOCK_SERVICE_ENDPOINT+"id="+productId,
                HttpMethod.GET,
                null,
                ProductInformation.class);
    }
}
