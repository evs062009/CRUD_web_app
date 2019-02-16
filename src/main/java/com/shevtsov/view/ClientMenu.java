package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatuses;

public class ClientMenu {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public ClientMenu(ClientService clientService, ProductService productService, OrderService orderService) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    MenuStatuses show() {
        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. List all products");
            System.out.println("2. Create order");
            System.out.println("3. Show my orders");
            System.out.println("4. Remove order");
            System.out.println("5. Modify user information");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    listAllProducts();
                    break;
                case "2":
                    createOrder();
                    break;
                case "3":
                    showMyOrders();
                    break;
                case "4":
                    removeOrder();
                    break;
                case "5":
                    modifyUserInformation();
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

    private void showMyOrders() {
        111
    }

    private void listAllProducts() {
        productService.listAllProducts();
        System.out.println("List of products:");
        System.out.println("...");
        System.out.println("...");
    }

    private void createOrder() {
        //показать все товары
        //показать товары в заказе
        createOrderMenu();
        if (orderService.createOrder()) {
            System.out.println("Order created");
        }
    }

    private void createOrderMenu() {
        while (true) {
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
                    111
                    return;
                case "E":
                    111
                    return;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
        }
    }

    private void removeProductFromOrder() {
    }

    private void addProductToOrder() {
        111
    }

    private void modifyUserInformation() {
        System.out.println("Input new name");
        String newName = ViewUtilities.inputString();
        System.out.println("Input new surname");
        String newSurname = ViewUtilities.inputString();
        System.out.println("Input new phone number");
        String newPhone = ViewUtilities.inputString();
        if (clientService.modifyUserInformation(newName, newSurname, newPhone)) {
            System.out.println("Information modified");
        }
    }
}
