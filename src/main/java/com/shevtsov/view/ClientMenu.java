package com.shevtsov.view;

import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.utilities.MyUtilities;
import com.shevtsov.view.enums.MenuStatuses;

public class ClientMenu {
    private final ClientService clientService;

    public ClientMenu(ClientService clientService) {
        this.clientService = clientService;
    }

    private MenuStatuses showMenuWorkWithClients() {
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
                    showAllClients(clientService);
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

    MenuStatuses show(ClientService clientService, ProductService productService, OrderService orderService) {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
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
        System.out.println("...");
        System.out.println("...");
        System.out.println("Input product id");
        long productID = MyUtilities.inputLong();
        orderService.removeProductFromBasket(productID);
    }

    private void modifyUserInformation(ClientService clientService) {
        System.out.println("Input new name");
        String newName = MyUtilities.inputString();
        System.out.println("Input new surname");
        String newSurname = MyUtilities.inputString();
        System.out.println("Input new phone number");
        String newPhone = MyUtilities.inputString();
        if (clientService.modifyUserInformation(newName, newSurname, newPhone)) {
            System.out.println("Information modified");
        }
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

    private void showAllClients(ClientService clientService) {
        //печатаем всех клиентов
        for (Client client : clientService.getAllClients()) {
            System.out.println(client);
        }
    }

}
