//package com.shevtsov.servlets;
//
//import com.shevtsov.dao.ClientDao;
//import com.shevtsov.dao.DBConnection;
//import com.shevtsov.dao.OrderDao;
//import com.shevtsov.dao.ProductDao;
//import ClientDBDao;
//import DBConnectionWorkDB;
//import OrderDBDao;
//import ProductDBDao;
//import com.shevtsov.domain.Order;
//import com.shevtsov.services.ClientService;
//import com.shevtsov.services.OrderService;
//import com.shevtsov.services.ProductService;
//import com.shevtsov.services.impl.AuthorisationImpl;
//import com.shevtsov.services.impl.ClientServiceImpl;
//import com.shevtsov.services.impl.OrderServiceImpl;
//import com.shevtsov.services.impl.ProductServiceImpl;
//import com.shevtsov.validators.ValidationService;
//import com.shevtsov.validators.impl.ValidationServiceImpl;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//    public class WebApp implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        DBConnection dbConnection = new DBConnectionWorkDB();
//        ClientDao clientDao = new ClientDBDao(dbConnection);
//        ValidationService validationService = new ValidationServiceImpl(clientDao);
//        AuthorisationImpl authorisation = new AuthorisationImpl(clientDao);
//        ClientService clientService = new ClientServiceImpl(validationService, clientDao, authorisation);
//        ProductDao productDao = new ProductDBDao(dbConnection);
//        ProductService productService = new ProductServiceImpl(productDao);
//        OrderDao orderDao = new OrderDBDao(dbConnection);
//        Order draft = new Order();
//        OrderService orderService = new OrderServiceImpl(clientDao, authorisation, orderDao, productDao, draft);
//
//        ServletContext servletContext = sce.getServletContext();
//        servletContext.addServlet("ClientServlet", new ClientServlet(clientService)).
//                addMapping("/clients/*");
//        servletContext.addServlet("ProductServlet", new ProductServlet(productService)).
//                addMapping("/products/*");
//        servletContext.addServlet("OrderServlet", new OrderServlet(orderService)).
//                addMapping("/orders/*");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
//}