package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.dto.RestaurantDTO;
import in.spring_react_practice.Spring_React_Application.model.Restaurant;
import in.spring_react_practice.Spring_React_Application.model.User;
import in.spring_react_practice.Spring_React_Application.request.CreateRestaurantRequest;
import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurants(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDTO addToFavourites(Long id, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
