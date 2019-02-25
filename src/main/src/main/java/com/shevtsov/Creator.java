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
import com.shevtsov.view.*;

public class Creator {
    public static MainMenu create(){
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(validationService);
        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        Authorisation authorisation = AuthorisationImpl.getInstance();
        EditOrderMenu editOrderMenu = new EditOrderMenu(orderService, productService);
        MenuWorkWithClients menuWorkWithClients = new MenuWorkWithClients(clientService);
        MenuWorkWithProducts menuWorkWithProducts = new MenuWorkWithProducts(productService);
        MenuWorkWithOrders menuWorkWithOrders = new MenuWorkWithOrders(orderService, editOrderMenu);
        AdminMenu adminMenu = new AdminMenu(menuWorkWithClients, menuWorkWithProducts, menuWorkWithOrders);
        ClientMenu clientMenu = new ClientMenu(clientService, productService, orderService, editOrderMenu);
        return new MainMenu(authorisation, adminMenu, clientMenu, clientService);
    }
}