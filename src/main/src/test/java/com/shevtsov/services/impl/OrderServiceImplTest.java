package com.shevtsov.services.impl;

import com.shevtsov.dao.ClientDao;
import com.shevtsov.dao.OrderDao;
import com.shevtsov.dao.ProductDao;
import com.shevtsov.domain.Client;
import com.shevtsov.domain.Order;
import com.shevtsov.domain.Product;
import com.shevtsov.services.OrderService;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

public class OrderServiceImplTest {
    private OrderService orderService;
    @Mock
    ClientDao clientDao;
    @Mock
    ProductDao productDao;
    @Mock
    OrderDao orderDao;
    @Mock
    AuthorisationImpl authorisation;
    @Mock
    Order draft;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private long id;

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl(clientDao, authorisation, orderDao, productDao);
        id = 1;
    }

    @After
    public void tearDown() {
        orderService = null;
    }

    @Test
    public void getAll() {
        //GIVEN
        Mockito.when(orderDao.getAll()).thenReturn(new ArrayList<>());
        //WHEN
        List<Order> actual = orderService.getAll();
        //THEN
        Mockito.verify(orderDao, times(1)).getAll();
        Assert.assertNotNull(actual);
    }

//    @Test
//    public void saveDoSave() {
//        //GIVEN
//        List<Product> products = new ArrayList<>();
//        products.add(new Product(1, "name", BigDecimal.valueOf(10)));
//        Mockito.when(draft.getProducts()).thenReturn(products);
//        //WHEN
//        boolean actual = orderService.save();
//        //THEN
////        Mockito.verifyZeroInteractions(draft);
////        Mockito.verifyZeroInteractions(orderDao);
//        Assert.assertTrue(actual);
//    }

    @Test
    public void saveWithEmptyProductsList() {
        //WHEN
        boolean actual = orderService.save();
        //THEN
        Mockito.verifyZeroInteractions(draft);
        Mockito.verifyZeroInteractions(orderDao);
        Assert.assertFalse(actual);
    }

    @Test
    public void removeWithValidParameters() {
        //GIVEN
        Order order = new Order(new Client(1, "name", "surname", 10, "phone", "email"));
        Mockito.when(orderDao.findByID(id)).thenReturn(Optional.of(order));
        Mockito.when(authorisation.getCurrentUserID()).thenReturn(-1L);
        Mockito.when(authorisation.getCurrentUserID()).thenReturn(order.getClient().getId());
        //WHEN
        boolean actual = orderService.remove(id);
        //THEN
        Mockito.verify(orderDao, times(2)).findByID(id);
        Mockito.verify(orderDao, times(1)).remove(id);
        Assert.assertTrue(actual);
    }

    @Test
    public void removeWithInvalidID() {
        //GIVEN
        Mockito.when(orderDao.findByID(id)).thenReturn(Optional.empty());
        //WHEN
        boolean actual = orderService.remove(id);
        //THEN
        Mockito.verify(orderDao, times(1)).findByID(id);
        Mockito.verifyNoMoreInteractions(orderDao);
        Mockito.verifyZeroInteractions(authorisation);
        Assert.assertFalse(actual);
    }

    @Test
    public void getUserOrders() {
        //GIVEN
        long currentUserID = 1;
        Mockito.when(authorisation.getCurrentUserID()).thenReturn(currentUserID);
        Mockito.when(orderDao.getUserOrders(currentUserID)).thenReturn(new ArrayList<>());
        //WHEN
        List<Order> actual = orderService.getUserOrders();
        //THEN
        Mockito.verify(orderDao, times(1)).getUserOrders(anyLong());
        Assert.assertNotNull(actual);
    }

    @Test
    public void addProductToDraftWithValidParameters() {
        //GIVEN
        long productID = 1;
        Product product = new Product(productID, "name", BigDecimal.valueOf(10));
        Mockito.when(productDao.findByID(productID)).thenReturn(Optional.of(product));
        //WHEN
        boolean actual = orderService.addProductToDraft(productID);
        //THEN
        Mockito.verify(productDao, times(2)).findByID(productID);
        Assert.assertTrue(actual);
    }

    @Test
    public void addProductToDraftWithInvalidID() {
        //GIVEN
        long productID = -1;
        Mockito.when(productDao.findByID(productID)).thenReturn(Optional.empty());
        //WHEN
        boolean actual = orderService.addProductToDraft(productID);
        //THEN
        Mockito.verify(productDao, times(1)).findByID(productID);
        Assert.assertFalse(actual);
    }

    @Test
    public void removeProductFromDraftWithValidParameters() {
        //GIVEN
        long productID = -1;
        //WHEN
        boolean actual = orderService.removeProductFromDraft(productID);
        //THEN
        Assert.assertFalse(actual);
    }

    @Test
    public void copyOrderToDraftWithValidParameters() {
        //GIVEN
        Order order = new Order(id, new Client("name", "surname", "phone"), new ArrayList<>());
        Mockito.when(orderDao.findByID(id)).thenReturn(Optional.of(order));
        //WHEN
        boolean actual = orderService.copyOrderToDraft(id);
        //THEN
        Mockito.verify(orderDao, times(2)).findByID(id);
        Assert.assertTrue(actual);
    }

    @Test
    public void copyOrderToDraftWithInvalidID() {
        //GIVEN
        Mockito.when(orderDao.findByID(id)).thenReturn(Optional.empty());
        //WHEN
        boolean actual = orderService.copyOrderToDraft(id);
        //THEN
        Mockito.verify(orderDao, times(1)).findByID(id);
        Mockito.verifyNoMoreInteractions(orderDao);
        Assert.assertFalse(actual);
    }

    @Test
    public void createDraft() {
        //GIVEN
        long clientID = 1;
        Mockito.when(authorisation.getCurrentUserID()).thenReturn(clientID);
        Mockito.when(clientDao.findByID(clientID)).thenReturn(Optional.of(
                new Client("name", "surname", "phone")));
        //WHEN
        orderService.createDraft();
        //THEN
        Mockito.verify(clientDao, times(1)).findByID(clientID);
    }

    @Test
    public void getDraftProducts() {
        //WHEN
        List<?> actual = orderService.getDraftProducts();
        //THEN
        Assert.assertNotNull(actual);
    }
}