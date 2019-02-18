package com.shevtsov;

import com.shevtsov.services.Authorisation;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.ProductService;
import com.shevtsov.services.impl.AuthorisationImpl;
import com.shevtsov.services.impl.ClientServiceImpl;
import com.shevtsov.services.impl.OrderServiceImpl;
import com.shevtsov.services.impl.ProductServiceImpl;
import com.shevtsov.validators.ValidationService;
import com.shevtsov.validators.impl.ValidationServiceImpl;
import com.shevtsov.view.AdminMenu;
import com.shevtsov.view.ClientMenu;
import com.shevtsov.view.MainMenu;
import com.shevtsov.view.OrderMenu;

public class App {
    public static void main(String[] args) {
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(validationService);
        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Authorisation authorisation = AuthorisationImpl.getInstance();
        OrderMenu orderMenu = new OrderMenu(orderService, productService);
        AdminMenu adminMenu = new AdminMenu(clientService, productService, orderService, orderMenu);
        ClientMenu clientMenu = new ClientMenu(clientService, productService, orderService, orderMenu);
        MainMenu mainMenu = new MainMenu(authorisation, adminMenu, clientMenu, clientService);

        System.out.println("Welcome)");
        mainMenu.show();
        System.out.println("Bye)");
    }
}