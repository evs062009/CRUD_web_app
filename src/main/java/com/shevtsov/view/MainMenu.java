package com.shevtsov.view;

import com.shevtsov.services.Authorisation;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.view.utilities.MyUtilities;
import com.shevtsov.view.enums.MenuStatuses;

public class MainMenu {
    private final Authorisation authorisation);
    private final AdminMenu adminMenu;
    private final ClientMenu clientMenu;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public MainMenu(Authorisation authorisation, AdminMenu adminMenu, ClientMenu clientMenu,
                    ClientService clientService, ProductService productService, OrderService orderService) {
        this.authorisation = authorisation;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() {

        MenuStatuses menuStatuses = MenuStatuses.CONTINUE_WORK;
        while (!menuStatuses.equals(MenuStatuses.EXIT_PROGRAM)) {
            System.out.println("1. Client authorisation");
            System.out.println("2. Client registration");
            System.out.println("3. Admin authorisation");
            System.out.println("0. Exit");

            switch (MyUtilities.inputString()) {
                case "1":
                    if (authorizeClient(authorisation)) {
                        menuStatuses = clientMenu.show(clientService, productService, orderService);
                    } else {
                        System.out.println("There is no such client!!!");
                    }
                    break;
                case "2":
                    adminMenu.createClient(clientService);
                    menuStatuses = clientMenu.show(clientService, productService, orderService);
                    break;
                case "3":
                    if (authorizeAdmin(authorisation)) {
                        menuStatuses = adminMenu.show(clientService, productService, orderService);
                    } else {
                        System.out.println("You are not an admin!!!");
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input!!!");
                    break;
            }
            System.out.println();
        }
    }

    private boolean authorizeClient(Authorisation authorisation) {
        System.out.println("Input phone number:");
        String phone = MyUtilities.inputString();
        return authorisation.authorizeClient(phone);
    }

    private boolean authorizeAdmin(Authorisation authorisation) {
        System.out.println("Input password:");
        MyUtilities.inputString();
        return authorisation.authorizeAdmin();
    }
}