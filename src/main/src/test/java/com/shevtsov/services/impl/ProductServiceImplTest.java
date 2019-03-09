package com.shevtsov.services.impl;

import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Product;
import com.shevtsov.exceptions.ObjectNotFoundExeption;
import com.shevtsov.services.ProductService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ProductServiceImplTest {
    @Mock
    ProductDao productDao;
    private ProductService productService;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private long id;
    private String name;
    private BigDecimal price;

    @Before
    public void setUp() {
        productService = new ProductServiceImpl(productDao);
        id = 1;
        name = "name";
        price = BigDecimal.valueOf(10L);
    }

    @After
    public void tearDown() {
        productService = null;
    }

    @Test
    public void create() {
        //GIVEN
        Mockito.when(productDao.save(any(Product.class))).thenReturn(true);
        //WHEN
        boolean actual = productService.create(name, price);
        //THEN
        Mockito.verify(productDao, times(1)).save(any(Product.class));
        Assert.assertTrue(actual);
    }

    @Test
    public void createUnsuccessful() {
        //GIVEN
        Mockito.when(productDao.save(any(Product.class))).thenReturn(false);
        //WHEN
        boolean actual = productService.create(name, price);
        //THEN
        Mockito.verify(productDao, times(1)).save(any(Product.class));
        Assert.assertFalse(actual);
    }

    @Test
    public void modifyWithValidParameters() {
        //GIVEN
        Mockito.when(productDao.isContainsKey(id)).thenReturn(true);
        Mockito.when(productDao.modify(any(Product.class))).thenReturn(true);
        //WHEN
        boolean actual = productService.modify(id, name, price);
        //THEN
        Mockito.verify(productDao, times(1)).isContainsKey(id);
        Mockito.verify(productDao, times(1)).modify(any(Product.class));
        Assert.assertTrue(actual);
    }

    @Test
    public void modifyWithInvalidID() {
        //GIVEN
        id = -1;
        Mockito.when(productDao.isContainsKey(id)).thenReturn(false);
        //WHEN
        boolean actual = productService.modify(id, name, price);
        //THEN
        Mockito.verify(productDao, times(1)).isContainsKey(id);
        Mockito.verifyNoMoreInteractions(productDao);
        Assert.assertFalse(actual);
    }

    @Test
    public void removeWithValidParameters() {
        //GIVEN
        Mockito.when(productDao.isContainsKey(id)).thenReturn(true);
        Mockito.when(productDao.remove(id)).thenReturn(true);
        //WHEN
        boolean actual = productService.remove(id);
        //THEN
        Mockito.verify(productDao, times(1)).isContainsKey(id);
        Mockito.verify(productDao, times(1)).remove(id);
        Assert.assertTrue(actual);
    }

    @Test
    public void removeWithInvalidID() {
        //GIVEN
        Mockito.when(productDao.isContainsKey(id)).thenReturn(false);
        //WHEN
        boolean actual = productService.remove(id);
        //THEN
        Mockito.verify(productDao, times(1)).isContainsKey(id);
        Mockito.verifyNoMoreInteractions(productDao);
        Assert.assertFalse(actual);
    }

    @Test
    public void gatAll() {
        //GIVEN
        Mockito.when(productDao.getAll()).thenReturn(new ArrayList<>());
        //WHEN
        List<Product> actual = productService.getAll();
        //THEN
        Mockito.verify(productDao, times(1)).getAll();
        Assert.assertNotNull(actual);
    }

    @Test
    public void getProductWithValidParameters() {
        //GIVEN
        Product expected = new Product(id, name, price);
        Mockito.when(productDao.findByID(id)).thenReturn(Optional.of(expected));
        //WHEN
        Product actual = productService.getProduct(id);
        //THEN
        Mockito.verify(productDao, times(1)).findByID(id);
        Assert.assertEquals(actual, expected);
    }

    @Test (expected = ObjectNotFoundExeption.class)
    public void getProductWithInvalidID() {
        //GIVEN
        id = -1;
        Mockito.when(productDao.findByID(id)).thenThrow(ObjectNotFoundExeption.class);
        //WHEN
        productService.getProduct(id);
        //THEN
        Mockito.verify(productDao, times(1)).findByID(id);
    }
}