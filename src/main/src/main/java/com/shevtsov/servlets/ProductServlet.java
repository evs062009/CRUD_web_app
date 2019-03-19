package com.shevtsov.servlets;

import com.shevtsov.domain.Product;
import com.shevtsov.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class ProductServlet extends AbstractServlet<Product> {
    private ProductService productService;

    ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    protected boolean create(HttpServletRequest req) throws NumberFormatException {
        String[] parameters = getParameters(req);
        return productService.create(parameters[0], BigDecimal.valueOf(Long.parseLong(parameters[1])));
    }

    @Override
    protected boolean modify(HttpServletRequest req, long id) throws NumberFormatException {
        String[] parameters = getParameters(req);
        return productService.modify(id, parameters[0], BigDecimal.valueOf(Long.parseLong(parameters[1])));
    }

    @Override
    protected boolean remove(long id) {
        return productService.remove(id);
    }

    private String[] getParameters(HttpServletRequest req) {
        String[] parameters = new String[2];
        parameters[0] = req.getParameter("name");
        parameters[1] = req.getParameter("price");
        return parameters;
    }
}