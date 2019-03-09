package com.shevtsov.view;

import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.viewUtilities.ViewUtilities;

public class EditOrderMenu {
    private final OrderService orderService;
    private final ProductService productService;

    public EditOrderMenu(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    void show() {
        while (true) {
            System.out.println("Goods in stock:");
            ViewUtilities.showList(productService.getAll());
            System.out.println("Goods in order:");
            ViewUtilities.showList(orderService.getDraftProducts());

            System.out.println("1. Add product to the order");
            System.out.println("2. Remove product from the order");
            System.out.println("S. Save order and exit");
            System.out.println("E. Exit to previous menu without saving");

            switch (ViewUtilities.inputString()) {
                case "1":
                    addProductToOrder();
                    break;
                case "2":
                    removeProductFromOrder();
                    break;
                case "S":
                    if (orderService.save()) {
                        System.out.println("Order created successfully.");
                    }
                    return;
                case "E":
                    return;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
        }
    }

    private void addProductToOrder() {
        System.out.println("Input product ID");
        long productID = ViewUtilities.inputLong();
        if (!orderService.addProductToDraft(productID)){
            System.out.println("There is no such product!!!");
        }
    }

    private void removeProductFromOrder() {
        System.out.println("Input product ID");
        long productID = ViewUtilities.inputLong();
        if (orderService.removeProductFromDraft(productID)) {
            System.out.println("There is no such product!!!");
        }
    }
}