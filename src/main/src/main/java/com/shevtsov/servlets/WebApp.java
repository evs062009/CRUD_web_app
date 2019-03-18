package com.shevtsov.servlets;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDBDao;
import com.shevtsov.dao.impl.DBConnectionWorkDB;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.impl.AuthorisationImpl;
import com.shevtsov.services.impl.ClientServiceImpl;
import com.shevtsov.validators.ValidationService;
import com.shevtsov.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClientDao clientDao = new ClientDBDao(new DBConnectionWorkDB());
        ValidationService validationService = new ValidationServiceImpl(clientDao);
        AuthorisationImpl authorisation = new AuthorisationImpl(clientDao);
        ClientService clientService = new ClientServiceImpl(validationService, clientDao, authorisation);

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("ClientServlet", new ClientServlet(clientService)).
                addMapping("/clients/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}