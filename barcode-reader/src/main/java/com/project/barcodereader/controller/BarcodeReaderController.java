package com.project.barcodereader.controller;

import com.project.barcodereader.entity.BarcodeInput;
import com.project.barcodereader.entity.BarcodeReaderServices;
import com.project.barcodereader.entity.ProductInformation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Hung Nguyen
 **/

@Controller
public class BarcodeReaderController {

    @Autowired
    BarcodeReaderServices barcodeReaderServices;

    @GetMapping("/read-barcode")
    public String getBarcode(Model model) {
        model.addAttribute("BarcodeInput", new BarcodeInput());
        return "front-page";
    }

    @PostMapping("/read-barcode")
    public String returnBarcodeResult(Model model, @ModelAttribute BarcodeInput barcodeInput) {
        String barcodeResponse = readBarcode(barcodeInput.getBarcode());
        model.addAttribute("barcodeResponse", barcodeResponse);
        return "completed-retrieve";
    }


    public String readBarcode(int barcode) {
        ResponseEntity<ProductInformation> productInformationResponseEntity = barcodeReaderServices.getProductInformationFromStock(barcode);
        if (productInformationResponseEntity.hasBody()) {
            ProductInformation productInformation = productInformationResponseEntity.getBody();
            return "Barcode: " + productInformation.getProductId() + "\n" +
                    "Name: " + productInformation.getName() + "\n" +
                    "Description: " + productInformation.getDescription() + "\n" +
                    "Price: " + productInformation.getPrice();
        } else {
            return "Product not found";
        }
    }
}
