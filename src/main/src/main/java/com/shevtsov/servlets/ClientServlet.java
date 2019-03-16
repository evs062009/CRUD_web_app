package com.shevtsov.servlets;

import com.shevtsov.domain.Client;
import com.shevtsov.services.ClientService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClientServlet extends HttpServlet {
    private ClientService clientService;

    ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if ("/delete".equals(pathInfo)){
            doDelete(req, resp);
            return;
        }
        resp.setContentType("text/html");
        try (PrintWriter printWriter = resp.getWriter()) {
            List<Client> clients = clientService.getAll();
            printWriter.println("Clients:");
            for (Client client : clients) {
                printWriter.println("<h5>" + client + "<h5>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.create(name, surname, Integer.parseInt(age), phone, email);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        clientService.remove(Long.parseLong(id));
    }
}