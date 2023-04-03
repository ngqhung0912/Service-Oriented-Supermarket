package com.project.barcodereader.services;

import com.project.barcodereader.entity.ProductInformation;
import com.project.barcodereader.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class BarcodeReaderServices {
    @Autowired
    private RestTemplate restTemplate;

    private static final String STOCK_SERVICE_ID = "stockMicroservices";
    private static final String STOCK_SERVICE_ENDPOINT = "/stock-microservices/stock?";

//TODO This doesn't seems like the correct way to handle 404. Basically, if I query a product information and that information
// does not exist, then stockMicroservice return a 404 - without a body. restTemplate.exchange cannot handle that. This try-catch
// block seems like a temporary fix rather than a full fix.
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
}
