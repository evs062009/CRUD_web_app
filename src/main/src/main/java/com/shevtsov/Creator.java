package com.shevtsov;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.dao.impl.ClientDBDao;
import com.shevtsov.dao.impl.OrderDBDao;
import com.shevtsov.dao.impl.ProductDBDao;
import com.shevtsov.domain.Order;
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

import java.util.ArrayList;

public class Creator {
    public MainMenu create(){
        ClientDao clientDao = new ClientDBDao();
        ProductDao productDao = new ProductDBDao();
        OrderDao orderDao = new OrderDBDao();
        ValidationService validationService = new ValidationServiceImpl(clientDao);
        AuthorisationImpl authorisation = new AuthorisationImpl(clientDao);
        ClientService clientService = new ClientServiceImpl(validationService, clientDao, authorisation);
        ProductService productService = new ProductServiceImpl(productDao);
        OrderService orderService = new OrderServiceImpl(clientDao, authorisation, orderDao, productDao,
                new Order(-1, null, new ArrayList<>()));
        EditOrderMenu editOrderMenu = new EditOrderMenu(orderService, productService);
        MenuWorkWithClients menuWorkWithClients = new MenuWorkWithClients(clientService);
        MenuWorkWithProducts menuWorkWithProducts = new MenuWorkWithProducts(productService);
        MenuWorkWithOrders menuWorkWithOrders = new MenuWorkWithOrders(orderService, editOrderMenu);
        AdminMenu adminMenu = new AdminMenu(menuWorkWithClients, menuWorkWithProducts, menuWorkWithOrders);
        ClientMenu clientMenu = new ClientMenu(clientService, productService, orderService, editOrderMenu,
                authorisation);
        return new MainMenu(authorisation, adminMenu, clientMenu, clientService);
    }
}