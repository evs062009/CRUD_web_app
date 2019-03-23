package com.shevtsov.servlets;

import com.shevtsov.domain.Product;
import com.shevtsov.dto.ProductDto;
import com.shevtsov.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    protected boolean create(HttpServletRequest req, HttpServletResponse resp) {
        Optional<ProductDto> optional = getParameters(req, resp);
        if (optional.isPresent()) {
            ProductDto productDto = optional.get();
            return productService.create(productDto.getName(), productDto.getPrice());
        } else {
            return false;
        }
    }

    @Override
    protected boolean modify(HttpServletRequest req, HttpServletResponse resp, long id) {
        Optional<ProductDto> optional = getParameters(req, resp);
        if (optional.isPresent()) {
            ProductDto productDto = optional.get();
            return productService.modify(id, productDto.getName(), productDto.getPrice());
        } else {
            return false;
        }
    }

    @Override
    protected boolean remove(long id) {
        return productService.remove(id);
    }

    private Optional<ProductDto> getParameters(HttpServletRequest req, HttpServletResponse resp) {
        BigDecimal price;
        try {
            price = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ServletUtilities.printMsg(resp, "Invalid price input!!!");
            return Optional.empty();
        }
        return Optional.of(new ProductDto(req.getParameter("name"), price));
    }
}