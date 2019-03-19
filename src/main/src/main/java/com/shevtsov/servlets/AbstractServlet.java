package com.shevtsov.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public abstract class AbstractServlet<E> extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<E> domains = getAll();
        try (PrintWriter printWriter = resp.getWriter()) {
            for (E domain : domains) {
                printWriter.println("<h5>" + domain + "<h5>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (create(req)) {
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
        try {
            if (modify(req, Long.parseLong(id))) {
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
            if (remove(Long.parseLong(id))) {
                return;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        printMsg(resp, "Removing has not been done.");
    }

    private void printMsg(HttpServletResponse resp, String msg) {
        try (PrintWriter printWriter = resp.getWriter()) {
            printWriter.println("<h5>" + msg + "<h5>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract List<E> getAll();

    protected abstract boolean create(HttpServletRequest req);

    protected abstract boolean modify(HttpServletRequest req, long id);

    protected abstract boolean remove(long id);
}