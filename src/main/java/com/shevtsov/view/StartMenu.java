package com.shevtsov.view;

import com.shevtsov.domain.Client;
import com.shevtsov.services.Authorisation;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.services.impl.AutorisationImpl;
import com.shevtsov.services.impl.ClientServiceImpl;
import com.shevtsov.services.impl.OrderServiceImpl;
import com.shevtsov.services.impl.ProductServiceImpl;
import com.shevtsov.utilities.MyUtilities;

public class StartMenu {

    public void show() {
        final Authorisation authorisation = new AutorisationImpl();
        final AdminMenu adminMenu = new AdminMenu();
        final ClientMenu clientMenu = new ClientMenu();
        final ClientService clientService = new ClientServiceImpl();
        final ProductService productService = new ProductServiceImpl();
        final OrderService orderService = new OrderServiceImpl();
        Client currentClient;

        while (true) {
            currentClient = null;

            System.out.println("1. Client authorisation");
            System.out.println("2. Client registration");
            System.out.println("3. Admin authorisation");
            System.out.println("0. Exit");

            switch (MyUtilities.inputString()) {
                case "2":
                    currentClient = adminMenu.createClient(clientService);

                    //without 'break' because we go to user menu just after registration
                case "1":
                    if (currentClient == null) {
                        currentClient = authorizeClient(authorisation);
                    }

                    //this if must be after registration (createClient()) or authorisation (authorizeClient())
                    if (currentClient != null) {
                        clientMenu.show(currentClient, clientService, productService, orderService);
                    }
                    break;
                case "3":
                    if (authorizeAdmin(authorisation)) {
                        adminMenu.show(clientService, productService, orderService);
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid input!!! Please repeat");
                    break;
            }
        }
    }

    private Client authorizeClient(Authorisation authorisation) {
        System.out.println("Input phone number:");
        String phone = MyUtilities.inputString();
        return authorisation.authorizeClient(phone);
    }

    private boolean authorizeAdmin(Authorisation authorisation) {
        System.out.println("Input password:");
        String password = MyUtilities.inputString();
        return authorisation.authorizeAdmin(password);
    }
}