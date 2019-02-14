package com.shevtsov.view;

import com.shevtsov.services.Authorisation;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.services.impl.AuthorisationImpl;
import com.shevtsov.services.impl.ClientServiceImpl;
import com.shevtsov.services.impl.OrderServiceImpl;
import com.shevtsov.services.impl.ProductServiceImpl;
import com.shevtsov.utilities.MyUtilities;

public class MainMenu {

    public void show() {
        final Authorisation authorisation = new AuthorisationImpl();
        final AdminMenu adminMenu = new AdminMenu();
        final ClientMenu clientMenu = new ClientMenu();
        final ClientService clientService = new ClientServiceImpl();
        final ProductService productService = new ProductServiceImpl();
        final OrderService orderService = new OrderServiceImpl();

        while (true) {
            System.out.println("1. Client authorisation");
            System.out.println("2. Client registration");
            System.out.println("3. Admin authorisation");
            System.out.println("0. Exit");

            switch (MyUtilities.inputString()) {
                case "1":
                    if (authorizeClient(authorisation)) {
                        clientMenu.show(clientService, productService, orderService);
                    } else {
                        System.out.println("There is no such client!!!");
                    }
                    break;
                case "2":
                    adminMenu.createClient(clientService);
                    clientMenu.show(clientService, productService, orderService);
                    break;
                case "3":
                    if (authorizeAdmin(authorisation)) {
                        adminMenu.show(clientService, productService, orderService);
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
        String password = MyUtilities.inputString();
        return authorisation.authorizeAdmin();
    }
}