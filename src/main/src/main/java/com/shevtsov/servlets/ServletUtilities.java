//package com.shevtsov.servlets;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class ServletUtilities {
//
//    static void printMsg(HttpServletResponse resp, String msg) {
//        try (PrintWriter printWriter = resp.getWriter()) {
//            printWriter.println("<h5>" + msg + "<h5>");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}