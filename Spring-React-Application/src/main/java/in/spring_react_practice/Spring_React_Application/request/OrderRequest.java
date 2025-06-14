package in.spring_react_practice.Spring_React_Application.request;

import in.spring_react_practice.Spring_React_Application.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}