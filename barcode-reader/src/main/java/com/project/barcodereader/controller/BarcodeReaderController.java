package com.project.barcodereader.controller;

import com.project.barcodereader.entity.BarcodeInput;
import com.project.barcodereader.exception.ProductNotFoundException;
import com.project.barcodereader.services.BarcodeReaderServices;
import org.springframework.beans.factory.annotation.Autowired;
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
        String barcodeResponse;
        try {
            barcodeResponse = barcodeReaderServices.getProductInformationFromStock(barcodeInput.getBarcode());
        } catch (ProductNotFoundException e) {
            barcodeResponse = e.getMessage();
        }
        model.addAttribute("barcodeResponse", barcodeResponse);
        return "completed-retrieve";
    }

}
