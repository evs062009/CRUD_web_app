package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.utilities.MyUtilities;

class ClientMenu {

    void show(ClientService clientService, ProductService productService, OrderService orderService) {
        while (true) {
            System.out.println("1. List all products");
            System.out.println("2. Add product to the basket");
            System.out.println("3. Remove product from the basket");
            System.out.println("4. Create order");
            System.out.println("5. Modify user information");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()) {
                case "1":
                    listAllProducts(productService);
                    break;
                case "2":
                    addProductToBasket(orderService);
                    break;
                case "3":
                    removeProductFromBasket(orderService);
                    break;
                case "4":
                    createOrder(orderService);
                    break;
                case "5":
                    modifyUserInformation(clientService);
                    break;
                case "9":
                    return;
                case "0":
                    System.out.println("need to implement");
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
    }

    private void listAllProducts(ProductService productService) {
        productService.listAllProducts();
        System.out.println("List of products:");
        System.out.println("...");
        System.out.println("...");
    }

    private void createOrder(OrderService orderService) {
        if (orderService.createOrder()) {
            System.out.println("Order created");
        }
    }

    private void addProductToBasket(OrderService orderService) {
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        orderService.addProductToBasket(productID);
    }

    private void removeProductFromBasket(OrderService orderService) {
        System.out.println("List the basket");
        System.out.println(orderService.getBasket());
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        orderService.removeProductFromBasket(productID);
    }

    private void modifyUserInformation(ClientService clientService) {
        System.out.println("Input new name");
        String name = MyUtilities.inputString();
        System.out.println("Input new surname");
        String surname = MyUtilities.inputString();
        System.out.println("Input new phone number");
        String phone = MyUtilities.inputString();
        if (clientService.modifyUserInformation(name, surname, phone)) {
            System.out.println("Information modified");
        }
    }
}
