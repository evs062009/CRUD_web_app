package com.shevtsov.controllers;

import com.shevtsov.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    private static final String JSP_NAME = "products";

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAll(ModelMap modelMap) {
        return ControllerUtilities.printMsg(modelMap, productService.getAll(), JSP_NAME);
    }

    @PostMapping
    public void add(ModelMap modelMap,
                    @RequestParam String name,
                    @RequestParam String price) {
        try {
            if (!productService.create(name, BigDecimal.valueOf(Long.parseLong(price)))) {
                ControllerUtilities.printMsg(modelMap, "Creating has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }

    @PutMapping
    public void modify(ModelMap modelMap,
                       @RequestParam String id,
                       @RequestParam String name,
                       @RequestParam String price) {
        try {
            if (!productService.modify(Long.parseLong(id), name, BigDecimal.valueOf(Long.parseLong(price)))) {
                ControllerUtilities.printMsg(modelMap, "Modifying has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }

    @DeleteMapping
    public void remove(ModelMap modelMap,
                       @RequestParam String id) {
        try {
            if (!productService.remove(Long.parseLong(id))) {
                ControllerUtilities.printMsg(modelMap, "Removing has not been done.", JSP_NAME);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ControllerUtilities.printMsg(modelMap, ControllerUtilities.invalidParamMsg, JSP_NAME);
        }
    }
}