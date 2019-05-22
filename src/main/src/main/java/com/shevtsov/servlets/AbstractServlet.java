//package com.shevtsov.servlets;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//public abstract class AbstractServlet<E> extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        List<E> domains = getAll();
//        try (PrintWriter printWriter = resp.getWriter()) {
//            for (E domain : domains) {
//                printWriter.println("<h5>" + domain + "<h5>");
//            }
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        if (!create(req, resp)) {
//            ServletUtilities.printMsg(resp, "Creating has not been done.");
//        }
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
//        if (!modify(req, resp, getID(req, resp))) {
//            ServletUtilities.printMsg(resp, "Modifying has not been done.");
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
//        if (!remove(getID(req, resp))) {
//            ServletUtilities.printMsg(resp, "Removing has not been done.");
//        }
//    }
//
//
//    protected abstract List<E> getAll();
//
//    protected abstract boolean create(HttpServletRequest req, HttpServletResponse resp);
//
//    protected abstract boolean modify(HttpServletRequest req, HttpServletResponse resp, long id);
//
//    protected abstract boolean remove(long id);
//
//    private long getID(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            return Long.parseLong(req.getParameter("id"));
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            ServletUtilities.printMsg(resp, "Invalid ID input!!!");
//        }
//        return -1;
//    }
//}