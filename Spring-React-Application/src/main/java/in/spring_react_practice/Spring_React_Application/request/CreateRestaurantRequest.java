package in.spring_react_practice.Spring_React_Application.request;

import in.spring_react_practice.Spring_React_Application.model.Address;
import in.spring_react_practice.Spring_React_Application.model.ContactInformation;
import lombok.Data;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;

}