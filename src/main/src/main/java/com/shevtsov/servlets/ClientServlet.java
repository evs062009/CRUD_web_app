package com.shevtsov.servlets;

import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientServlet extends AbstractServlet<Client> {
    private ClientService clientService;

    ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected List<Client> getAll() {
        return clientService.getAll();
    }

    @Override
    protected boolean create(HttpServletRequest req) throws NumberFormatException {
        String[] parameters = getParameters(req);
        return clientService.create(parameters[0], parameters[1], Integer.parseInt(parameters[2]), parameters[3],
                parameters[4]);
    }

    @Override
    protected boolean modify(HttpServletRequest req, long id) throws NumberFormatException {
        String[] parameters = getParameters(req);
        return clientService.modify(id, parameters[0], parameters[1], Integer.parseInt(parameters[2]), parameters[3],
                parameters[4]);
    }

    @Override
    protected boolean remove(long id) {
        return clientService.remove(id);
    }

    private String[] getParameters(HttpServletRequest req) {
        String[] parameters = new String[5];
        parameters[0] = req.getParameter("name");
        parameters[1] = req.getParameter("surname");
        parameters[2] = req.getParameter("age");
        parameters[3] = req.getParameter("phone");
        parameters[4] = req.getParameter("email");
        return parameters;
    }
}