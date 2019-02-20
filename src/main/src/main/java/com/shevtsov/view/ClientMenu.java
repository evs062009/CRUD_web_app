package com.shevtsov.view;

import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.services.impl.AuthorisationImpl;
import com.shevtsov.view.viewUtilities.ViewUtilities;
import com.shevtsov.view.viewEnums.MenuStatus;

public class ClientMenu {
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;
    private final EditOrderMenu editOrderMenu;
    private final AuthorisationImpl authorisation = AuthorisationImpl.getInstance();

    public ClientMenu(ClientService clientService, ProductService productService, OrderService orderService,
                      EditOrderMenu editOrderMenu) {
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
        this.editOrderMenu = editOrderMenu;
    }

    MenuStatus show() {
        MenuStatus menuStatus = MenuStatus.CONTINUE_WORK;
        while (!menuStatus.equals(MenuStatus.EXIT_PROGRAM)) {
            System.out.println("1. List all products");
            System.out.println("2. Create order");
            System.out.println("3. Show my orders");
            System.out.println("4. Remove order");
            System.out.println("5. Modify account");
            System.out.println("R. Return to previous menu");
            System.out.println("E. Exit program");

            switch (ViewUtilities.inputString()) {
                case "1":
                    ViewUtilities.showList(productService.gatAll());
                    break;
                case "2":
                    createOrder();
                    break;
                case "3":
                    ViewUtilities.showList(orderService.getUserOrders());
                    break;
                case "4":
                    removeOrder();
                    break;
                case "5":
                    modifyAccount();
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

    private void createOrder() {
        orderService.createDraft();
        editOrderMenu.show();
    }

    private void removeOrder() {
        System.out.println("Input order id");
        long orderID = ViewUtilities.inputLong();
        if (orderService.remove(orderID)) {
            System.out.println("Order removed.");
        }
    }

    private void modifyAccount() {
        System.out.println(clientService.getClient(authorisation.getCurrentUserID()));
        System.out.println("Input new name");
        String newName = ViewUtilities.inputString();
        System.out.println("Input new surname");
        String newSurname = ViewUtilities.inputString();
        System.out.println("Input new age:");
        int newAge = ViewUtilities.inputInt();
        System.out.println("Input new phone number");
        String newPhone = ViewUtilities.inputString();
        System.out.println("Input email:");
        String newEmail = ViewUtilities.inputString();
        if (clientService.modifyAccount(newName, newSurname, newAge, newPhone, newEmail)) {
            System.out.println("Information modified");
        }
    }
}