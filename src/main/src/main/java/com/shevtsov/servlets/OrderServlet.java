package com.shevtsov.servlets;

import com.shevtsov.domain.Order;
import com.shevtsov.services.OrderService;
import com.shevtsov.services.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OrderServlet extends AbstractServlet<Order> {
    private OrderService orderService;

    OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected List<Order> getAll() {
        return orderService.getAll();
    }

    @Override
    protected boolean create(HttpServletRequest req) throws NumberFormatException {
        String currentUserPhone = req.getParameter("phone");
        OrderServiceImpl orderServiceImpl = (OrderServiceImpl) orderService;
        if (orderServiceImpl.getAuthorisation().authorizeClient(currentUserPhone)) {
            orderService.createDraft();
            return saveNewOrderSpecification(req);
        }
        return false;
    }

    @Override
    protected boolean modify(HttpServletRequest req, long id) throws NumberFormatException {
        if (orderService.copyOrderToDraft(id)) {
            return saveNewOrderSpecification(req);
        }
        return false;
    }

    @Override
    protected boolean remove(long id) {
        return orderService.remove(id);
    }

    private boolean saveNewOrderSpecification(HttpServletRequest req) throws NumberFormatException {
        String productID = req.getParameter("productID");
        if (orderService.addProductToDraft(Long.parseLong(productID))) {
            return orderService.save();
        }
        return false;
    }
}