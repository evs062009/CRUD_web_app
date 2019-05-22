//package com.shevtsov.servlets;
//
//import com.shevtsov.domain.Order;
//import com.shevtsov.services.OrderService;
//import com.shevtsov.services.impl.OrderServiceImpl;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//public class OrderServlet extends AbstractServlet<Order> {
//    private OrderService orderService;
//
//    OrderServlet(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @Override
//    protected List<Order> getAll() {
//        return orderService.getAll();
//    }
//
//    @Override
//    protected boolean create(HttpServletRequest req, HttpServletResponse resp) {
//        String currentUserPhone = req.getParameter("phone");
//        OrderServiceImpl orderServiceImpl = (OrderServiceImpl) orderService;
//        if (orderServiceImpl.getAuthorisation().authorizeClient(currentUserPhone)) {
//            orderService.createDraft();
//            return saveNewOrderSpecification(req, resp);
//        }
//        return false;
//    }
//
//    @Override
//    protected boolean modify(HttpServletRequest req, HttpServletResponse resp, long id) {
//        if (orderService.copyOrderToDraft(id)) {
//            return saveNewOrderSpecification(req, resp);
//        }
//        return false;
//    }
//
//    @Override
//    protected boolean remove(long id) {
//        return orderService.remove(id);
//    }
//
//    private boolean saveNewOrderSpecification(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            long productID = Long.parseLong(req.getParameter("productID"));
//            if (orderService.addProductToDraft(productID)){
//                return orderService.save();
//            }
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            ServletUtilities.printMsg(resp, "Invalid product ID!!!");
//        }
//        return false;
//    }
//}