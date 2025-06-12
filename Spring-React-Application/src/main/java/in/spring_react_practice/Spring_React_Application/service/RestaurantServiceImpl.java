package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.dto.RestaurantDTO;
import in.spring_react_practice.Spring_React_Application.model.Address;
import in.spring_react_practice.Spring_React_Application.model.Restaurant;
import in.spring_react_practice.Spring_React_Application.model.User;
import in.spring_react_practice.Spring_React_Application.repository.AddressRepository;
import in.spring_react_practice.Spring_React_Application.repository.RestaurantRepository;
import in.spring_react_practice.Spring_React_Application.repository.UserRepository;
import in.spring_react_practice.Spring_React_Application.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest createRestaurantRequest, User user) {
        Address address = addressRepository.save(createRestaurantRequest.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(createRestaurantRequest.getContactInformation());
        restaurant.setCuisineType(createRestaurantRequest.getCuisineType());
        restaurant.setDescription(createRestaurantRequest.getDescription());
        restaurant.setImages(createRestaurantRequest.getImages());
        restaurant.setName(createRestaurantRequest.getName());
        restaurant.setOpeningHours(createRestaurantRequest.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);

    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(updatedRestaurant.getCuisineType() != null)
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        if(updatedRestaurant.getDescription() != null)
            restaurant.setDescription(updatedRestaurant.getDescription());
        if(updatedRestaurant.getName()!=null)
            restaurant.setName(updatedRestaurant.getName());
        if(updatedRestaurant.getOpeningHours()!=null)
            restaurant.setOpeningHours(updatedRestaurant.getOpeningHours());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if(opt.isEmpty())
            throw new Exception("no restaurant found with the id");
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant==null)
            throw new Exception("No associated restaurant with owner");
        return restaurant;
    }

    @Override
    public RestaurantDTO addToFavourites(Long id, User user) throws Exception {
       Restaurant restaurant = findRestaurantById(id);
       RestaurantDTO dto = new RestaurantDTO();
       dto.setDescription(restaurant.getDescription());
       dto.setImages(restaurant.getImages());
       dto.setTitle(restaurant.getName());
       dto.setId(id);

       if(user.getFavorites().contains(dto))
           user.getFavorites().remove(dto);
       else
           user.getFavorites().add(dto);
       userRepository.save(user);
       return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
