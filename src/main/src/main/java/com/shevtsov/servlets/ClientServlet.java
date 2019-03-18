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
        resp.setContentType("text/html");
        try (PrintWriter printWriter = resp.getWriter()) {
            List<Client> clients = clientService.getAll();
            for (Client client : clients) {
                printWriter.println("<h5>" + client + "<h5>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        try {
            if (clientService.create(name, surname, Integer.parseInt(age), phone, email)) {
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        printMsg(resp, "Creating has not been done.");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String newName = req.getParameter("newName");
        String newSurname = req.getParameter("newSurname");
        String newAge = req.getParameter("newAge");
        String newPhone = req.getParameter("newPhone");
        String newEmail = req.getParameter("newEmail");
        try {
            if (clientService.modify(Long.parseLong(id), newName, newSurname, Integer.parseInt(newAge),
                    newPhone, newEmail)) {
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        printMsg(resp, "Modifying has not been done.");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            if (clientService.remove(Long.parseLong(id))) {
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        printMsg(resp, "Removing has not been done (there is no such client)");
    }

    private void printMsg(HttpServletResponse resp, String msg) {
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.println("<h5>" + msg + "<h5>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}