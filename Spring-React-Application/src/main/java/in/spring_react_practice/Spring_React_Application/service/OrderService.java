package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.model.Order;
import in.spring_react_practice.Spring_React_Application.model.User;
import in.spring_react_practice.Spring_React_Application.request.OrderRequest;
import java.util.List;

public interface OrderService {

    Order createOrder(OrderRequest order, User user) throws Exception;

    Order updateOrder(Long orderId, String orderStatus) throws Exception;

    void cancelOrder(Long orderId) throws Exception;

    List<Order> getUserOrders(Long userId) throws Exception;

    List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws Exception;

    Order findOrderById(Long orderId) throws Exception;
}