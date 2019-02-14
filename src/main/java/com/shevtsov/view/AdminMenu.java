package com.shevtsov.view;

import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.utilities.MyUtilities;

import java.math.BigDecimal;

public class AdminMenu {

    public void show(ClientService clientService, ProductService productService, OrderService orderService){
        while (true) {
            System.out.println("1. Clients");
            System.out.println("2. Products");
            System.out.println("3. Orders");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()) {
                case "1":
                    showMenuWorkWithClients(clientService);
                    break;
                case "2":
                    showMenuWorkWithProducts(productService);
                    break;
                case "3":
                    showMenuWorkWithOrders(orderService);
                    break;
                case "9":
                    return;
                case "0":
                    System.out.println("!!!need to be implemented");
                    break;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
        }
    }

    private void showMenuWorkWithClients(ClientService clientService)  {
        while (true) {
            System.out.println("1. Add client");
            System.out.println("2. Modify client");
            System.out.println("3. Remove client");
            System.out.println("4. List all clients");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()) {
                case "1":
                    createClient(clientService);
                    break;
                case "2":
                    modifyClient(clientService);
                    break;
                case "3":
                    removeClient(clientService);
                    break;
                case "4":
                    clientService.listAllClients();
                    break;
                case "9":
                    return;
                case "0":
                    System.out.println("need to implement");
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
        }
    }

    private void showMenuWorkWithProducts(ProductService productService) {
        while (true) {
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. List all products");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()) {
                case "1":
                    addProduct(productService);
                    break;
                case "2":
                    modifyProduct(productService);
                    break;
                case "3":
                    removeProduct(productService);
                    break;
                case "4":
                    productService.listAllProducts();
                    break;
                case "9":
                    return;
                case "0":
                    System.out.println("need to implement");
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
        }
    }

    private void showMenuWorkWithOrders(OrderService orderService) {
        while (true) {
            System.out.println("1. List all orders");
            System.out.println("2. Show order");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()){
                case "1":
                    orderService.listAllOrder();
                    break;
                case "2":
                    showOrder(orderService);
                    break;
                case "9":
                    return;
                case "0":
                    System.out.println("need to implement");
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
        }
    }

    Client createClient(ClientService clientService) {
        System.out.println("Input name:");
        String name = MyUtilities.inputString();
        System.out.println("Input surname:");
        String surname = MyUtilities.inputString();
        System.out.println("Input phone:");
        String phone = MyUtilities.inputString();
        return clientService.createClient(name, surname, phone);
    }

    private void modifyClient(ClientService clientService) {
        System.out.println("Input client id");
        long clientID = MyUtilities.inputLong();
        System.out.println("Input name:");
        String name = MyUtilities.inputString();
        System.out.println("Input surname:");
        String surname = MyUtilities.inputString();
        System.out.println("Input phone:");
        String phone = MyUtilities.inputString();
        clientService.modifyClient(clientID, name, surname, phone);
    }

    private void removeClient(ClientService clientService) {
        System.out.println("Input client id");
        long clientID = MyUtilities.inputLong();
        clientService.removeClient(clientID);
    }

    private void addProduct(ProductService productService) {
        System.out.println("Input name:");
        String name = MyUtilities.inputString();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(MyUtilities.inputLong());
        productService.createProduct(name, price);
    }

    private void modifyProduct(ProductService productService) {
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        System.out.println("Input name:");
        String name = MyUtilities.inputString();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(MyUtilities.inputLong());
        productService.modifyProduct(productID, name, price);
    }

    private void removeProduct(ProductService productService) {
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        productService.removeProduct(productID);
    }

    private void showOrder(OrderService orderService) {
        System.out.println("Input order id:");
        long orderID = MyUtilities.inputLong();
        orderService.showOrder(orderID);
    }
}