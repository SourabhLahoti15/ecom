import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.OrderDAOImpl;
import model.Order;

public class OrderDAOImplTest {

    private OrderDAOImpl orderDAO;

    @BeforeEach
    void setUp() {
        orderDAO = new OrderDAOImpl();
    }

    @Test
    void testPlaceOrder() {
        Order order = new Order(1, 101, 1, 1, 500.0, "Pending", 2, new Timestamp(System.currentTimeMillis()));
        assertTrue(orderDAO.placeOrder(order));
    }

    @Test
    void testUpdateOrder() {
        Order order = new Order(1, 101, 1, 1, 500.0, "Pending", 2, new Timestamp(System.currentTimeMillis()));
        orderDAO.placeOrder(order);
        order.setorderAmount(550.0);
        order.setOrder_status("Shipped");
        assertTrue(orderDAO.updateOrder(order));
    }

    @Test
    void testDeleteOrder() {
        Order order = new Order(1, 101, 1, 1, 500.0, "Pending", 2, new Timestamp(System.currentTimeMillis()));
        orderDAO.placeOrder(order);
        assertTrue(orderDAO.deleteOrder(order.getOrderId()));
    }

    @Test
    void testGetOrderByUserId() {
        Order order = new Order(1, 101, 1, 1, 500.0, "Pending", 2, new Timestamp(System.currentTimeMillis()));
        orderDAO.placeOrder(order);
        List<Order> orders = orderDAO.getOrderByUserId(1);
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void testGetAllOrders() {
        Order order = new Order(1, 101, 1, 1, 500.0, "Pending", 2, new Timestamp(System.currentTimeMillis()));
        orderDAO.placeOrder(order);
        List<Order> orders = orderDAO.getAllOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void testGetOrderById() {
        Order order = new Order(1, 101, 1, 1, 500.0, "Pending", 2, new Timestamp(System.currentTimeMillis()));
        orderDAO.placeOrder(order);
        Order fetchedOrder = orderDAO.getOrderById(order.getOrderId());
        assertNotNull(fetchedOrder);
        assertEquals(order.getOrderId(), fetchedOrder.getOrderId());
    }
}
