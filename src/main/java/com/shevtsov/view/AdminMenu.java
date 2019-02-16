package com.shevtsov.view;

import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatuses;
import java.math.BigDecimal;

public class AdminMenu {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminMenu(ClientService clientService, ProductService productService, OrderService orderService) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    MenuStatuses show() {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. Clients");
            System.out.println("2. Products");
            System.out.println("3. Orders");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    menuStatuses = showMenuWorkWithClients();
                    break;
                case "2":
                    menuStatuses = showMenuWorkWithProducts();
                    break;
                case "3":
                    menuStatuses = showMenuWorkWithOrders();
                    break;
                case "R":
                    return MenuStatuses.CONTINUE_WORK;
                case "E":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private MenuStatuses showMenuWorkWithClients() {
        MenuStatuses menuStatus = MenuStatuses.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. Add client");
            System.out.println("2. Modify client");
            System.out.println("3. Remove client");
            System.out.println("4. List all clients");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    modifyClient();
                    break;
                case "3":
                    removeClient();
                    break;
                case "4":
                    showAllClients();
                    break;
                case "R":
                    return MenuStatuses.CONTINUE_WORK;
                case "E":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private MenuStatuses showMenuWorkWithProducts() {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. Add product");
            System.out.println("2. Modify product");
            System.out.println("3. Remove product");
            System.out.println("4. List all products");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    modifyProduct();
                    break;
                case "3":
                    removeProduct();
                    break;
                case "4":
                    listAllProducts();
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

    private MenuStatuses showMenuWorkWithOrders() {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. List all orders");
            System.out.println("2. Modify order");
            System.out.println("3. Remove order");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    listAllOrder();
                    break;
                case "2":
                    ModifyOrder();
                    break;
                case "3":
                    removeOrder();
                    break;
                case "R":
                    return MenuStatuses.CONTINUE_WORK;
                case "E":
                    return MenuStatuses.EXIT_PROGRAM;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
        return MenuStatuses.EXIT_PROGRAM;
    }

    private void removeOrder() {
        111
    }

    private void ModifyOrder() {
        111
    }

    private void showAllClients() {
        for (Client client : clientService.getAllClients()) {
            System.out.println(client);
        }
    }

    private void listAllProducts() {
        productService.listAllProducts();
        System.out.println("List of products:");
        System.out.println("...");
        System.out.println("...");
    }

    private void listAllOrder() {
        orderService.listAllOrder();
        System.out.println("List of orders:");
        System.out.println("...");
        System.out.println("...");
    }

    private void createClient() {
        System.out.println("Input name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input surname:");
        String surname = ViewUtilities.inputString();
        System.out.println("Input age:");
        int age = ViewUtilities.inputInt();
        System.out.println("Input phone number:");
        String phone = ViewUtilities.inputString();
        System.out.println("Input email:");
        String email = ViewUtilities.inputString();
        if (clientService.createClient(name, surname, age, phone, email)) {
            System.out.println("Client saved");
        }
    }

    private void modifyClient() {
        System.out.println("Input client id");
        long clientID = ViewUtilities.inputLong();
        System.out.println("Input new name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input new surname:");
        String surname = ViewUtilities.inputString();
        System.out.println("Input new age:");
        int age = ViewUtilities.inputInt();
        System.out.println("Input new phone number:");
        String phone = ViewUtilities.inputString();
        System.out.println("Input email:");
        String email = ViewUtilities.inputString();
        if (clientService.modifyClient(clientID, name, surname, age, phone, email)) {
            System.out.println("Client modified");
        }
    }

    private void removeClient() {
        System.out.println("Input client id");
        long clientID = ViewUtilities.inputLong();
        if (clientService.removeClient(clientID)) {
            System.out.println("Client removed");
        }
    }

    private void addProduct() {
        System.out.println("Input name:");
        String name = ViewUtilities.inputString();
        System.out.println("Input price:");
        BigDecimal price = BigDecimal.valueOf(ViewUtilities.inputLong());
        if (productService.createProduct(name, price)) {
            System.out.println("Product saved");
        }
    }

    private void modifyProduct() {
        System.out.println("Input product id");
        long productID = ViewUtilities.inputLong();
        System.out.println("Input new name:");
        String newName = ViewUtilities.inputString();
        System.out.println("Input new price:");
        BigDecimal newPrice = BigDecimal.valueOf(ViewUtilities.inputLong());
        if (productService.modifyProduct(productID, newName, newPrice)) {
            System.out.println("Product modified");
        }
    }

    private void removeProduct() {
        System.out.println("Input product id");
        long productID = ViewUtilities.inputLong();
        if (productService.removeProduct(productID)) {
            System.out.println("Product removed");
        }
    }
}