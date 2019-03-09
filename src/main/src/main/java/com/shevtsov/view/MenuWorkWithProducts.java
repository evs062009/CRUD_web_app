package com.shevtsov.view;

import com.shevtsov.domain.Product;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.viewEnums.MenuStatus;
import com.shevtsov.view.viewUtilities.ViewUtilities;

import java.math.BigDecimal;

public class MenuWorkWithProducts {
    private final ProductService productService;

    public MenuWorkWithProducts(ProductService productService) {
        this.productService = productService;
    }

    MenuStatus show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. List all products");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    create();
                    break;
                case "2":
                    modify();
                    break;
                case "3":
                    remove();
                    break;
                case "4":
                    ViewUtilities.showList(productService.getAll());
                    break;
                case "R":
                    return MenuStatus.CONTINUE_WORK;
                case "E":
                    return MenuStatus.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatus.EXIT_PROGRAM;
    }

    private void create() {
        System.out.println("Input name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(ViewUtilities.inputLong());
        if (productService.create(name, price)) {
            System.out.println("Product saved");
        }
    }

    private void modify() {
        System.out.println("Input product id");
        long productID = ViewUtilities.inputLong();
        try {
            Product product = productService.getProduct(productID);
            System.out.println(product);
            System.out.println("Input new name:");
            String newName = ViewUtilities.inputString();
            System.out.println("Input new price:");
            BigDecimal newPrice = BigDecimal.valueOf(ViewUtilities.inputLong());
            if (productService.modify(productID, newName, newPrice)) {
                System.out.println("Product modified");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void remove() {
        System.out.println("Input product id");
        long productID = ViewUtilities.inputLong();
        if (productService.remove(productID)) {
            System.out.println("Product removed");
        }
    }
}