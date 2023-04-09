package com.project.itemcomparision.controller;

import com.project.itemcomparision.service.ItemComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


// TODO this service is not yet functional. User need to input two barcodes/product name. this will query the product information
//  and return side-by-side.
@Controller
public class ItemComparisionController {

    @Autowired private ItemComparisonService itemComparisonService;


    @GetMapping("/")
    public String getItemInfo(Model model) {
        model.addAttribute("Input1", "");
        model.addAttribute("Input2", "");
        return "front-page";
    }

    @PostMapping("/")
    public String returnItemResult(Model model, @ModelAttribute String productInput1, @ModelAttribute String productInput2) {
        String productResponse;
        try {
            int productId = Integer.parseInt(productInput1);
            productResponse = itemComparisonService.getProductInformationFromStock(productId);

        } catch (NumberFormatException e) {
            productResponse = itemComparisonService.getProductInformationFromStock(productInput1);
        }
        model.addAttribute("barcodeResponse", productResponse);
        return "completed-retrieve";
    }
}
