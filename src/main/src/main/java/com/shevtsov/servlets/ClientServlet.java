package com.shevtsov.servlets;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.impl.ClientDBDao;
import com.shevtsov.dao.impl.DBConnectionWorkDB;
import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;
import com.shevtsov.services.impl.AuthorisationImpl;
import com.shevtsov.services.impl.ClientServiceImpl;
import com.shevtsov.validators.ValidationService;
import com.shevtsov.validators.impl.ValidationServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {
    private ClientDao clientDao = new ClientDBDao(new DBConnectionWorkDB());
    private ValidationService validationService = new ValidationServiceImpl(clientDao);
    private AuthorisationImpl authorisation = new AuthorisationImpl(clientDao);
    private ClientService clientService = new ClientServiceImpl(validationService, clientDao, authorisation);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try (PrintWriter printWriter = resp.getWriter()) {
            List<Client> clients = clientService.getAll();
            printWriter.println("Clients:");
            for (Client client : clients) {
                printWriter.println(client);
            }
        }
    }
}
