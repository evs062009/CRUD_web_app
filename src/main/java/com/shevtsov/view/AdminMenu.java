package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.utilities.MyUtilities;
import com.shevtsov.view.enums.MenuStatuses;

import java.math.BigDecimal;

class AdminMenu {

    MenuStatuses show(ClientService clientService, ProductService productService, OrderService orderService) {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. Clients");
            System.out.println("2. Products");
            System.out.println("3. Orders");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()) {
                case "1":
                    menuStatuses = showMenuWorkWithClients(clientService);
                    break;
                case "2":
                    menuStatuses = showMenuWorkWithProducts(productService);
                    break;
                case "3":
                    menuStatuses = showMenuWorkWithOrders(orderService);
                    break;
                case "9":
                    return MenuStatuses.CONTINUE_WORK;
                case "0":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private MenuStatuses showMenuWorkWithClients(ClientService clientService) {
        MenuStatuses menuStatus = MenuStatuses.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatuses.EXIT_PROGRAM)) {
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
                    listAllClients(clientService);
                    break;
                case "9":
                    return MenuStatuses.CONTINUE_WORK;
                case "0":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private MenuStatuses showMenuWorkWithProducts(ProductService productService) {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
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
                    listAllProducts(productService);
                    break;
                case "9":
                    return MenuStatuses.CONTINUE_WORK;
                case "0":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private void listAllClients(ClientService clientService) {
        clientService.listAllClients();
        System.out.println("List of clients:");
        System.out.println("...");
        System.out.println("...");
    }

    private void listAllProducts(ProductService productService) {
        productService.listAllProducts();
        System.out.println("List of products:");
        System.out.println("...");
        System.out.println("...");
    }

    private MenuStatuses showMenuWorkWithOrders(OrderService orderService) {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. List all orders");
            System.out.println("2. Show order");
            System.out.println("9. Return to previous menu");
            System.out.println("0. Exit program");

            switch (MyUtilities.inputString()) {
                case "1":
                    listAllOrder(orderService);
                    break;
                case "2":
                    showOrder(orderService);
                    break;
                case "9":
                    return MenuStatuses.CONTINUE_WORK;
                case "0":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private void listAllOrder(OrderService orderService) {
        orderService.listAllOrder();
        System.out.println("List of orders:");
        System.out.println("...");
        System.out.println("...");
    }

    void createClient(ClientService clientService) {
        System.out.println("Input name:");
        String name = MyUtilities.inputString();
        System.out.println("Input surname:");
        String surname = MyUtilities.inputString();
        System.out.println("Input phone:");
        String phone = MyUtilities.inputString();
        if (clientService.createClient(name, surname, phone)) {
            System.out.println("Client saved");
        }
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
        if (clientService.modifyClient(clientID, name, surname, phone)) {
            System.out.println("Client modified");
        }
    }

    private void removeClient(ClientService clientService) {
        System.out.println("Input client id");
        long clientID = MyUtilities.inputLong();
        if (clientService.removeClient(clientID)) {
            System.out.println("Client removed");
        }
    }

    private void addProduct(ProductService productService) {
        System.out.println("Input name:");
        String name = MyUtilities.inputString();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(MyUtilities.inputLong());
        if (productService.createProduct(name, price)) {
            System.out.println("Product saved");
        }
    }

    private void modifyProduct(ProductService productService) {
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        System.out.println("Input new name:");
        String newName = MyUtilities.inputString();
        System.out.println("Input new price:");
        BigDecimal newPrice = BigDecimal.valueOf(MyUtilities.inputLong());
        if (productService.modifyProduct(productID, newName, newPrice)) {
            System.out.println("Product modified");
        }
    }

    private void removeProduct(ProductService productService) {
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        if (productService.removeProduct(productID)) {
            System.out.println("Product removed");
        }
    }

    private void showOrder(OrderService orderService) {
        System.out.println("Input order id:");
        long orderID = MyUtilities.inputLong();
        if (orderService.findOrderByID(orderID)) {
            System.out.println("Order:");
            System.out.println("...");
            System.out.println("...");
        } else {
            System.out.println("There is no such order.");
        }
    }
}