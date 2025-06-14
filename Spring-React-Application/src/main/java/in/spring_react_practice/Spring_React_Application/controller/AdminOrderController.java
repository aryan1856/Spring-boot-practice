package in.spring_react_practice.Spring_React_Application.controller;

import in.spring_react_practice.Spring_React_Application.model.Order;
import in.spring_react_practice.Spring_React_Application.model.User;
import in.spring_react_practice.Spring_React_Application.request.OrderRequest;
import in.spring_react_practice.Spring_React_Application.service.OrderService;
import in.spring_react_practice.Spring_React_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id,
                                                       @RequestParam(required = false) String orderStatus) throws Exception {

//        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getRestaurantOrders(id, orderStatus);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/update/{id}/{status}")
    public ResponseEntity<Order> updateOrderStatus(@RequestHeader("Authorization") String jwt,
                                                       @PathVariable Long id,
                                                       @PathVariable String status) throws Exception {

//        User user = userService.findUserByJwtToken(jwt);
        Order updatedOrder = orderService.updateOrder(id, status);

        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}