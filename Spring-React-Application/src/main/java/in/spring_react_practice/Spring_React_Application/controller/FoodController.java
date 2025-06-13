package in.spring_react_practice.Spring_React_Application.controller;

import in.spring_react_practice.Spring_React_Application.model.Food;
import in.spring_react_practice.Spring_React_Application.model.Restaurant;
import in.spring_react_practice.Spring_React_Application.model.User;
import in.spring_react_practice.Spring_React_Application.request.CreateFoodRequest;
import in.spring_react_practice.Spring_React_Application.service.FoodService;
import in.spring_react_practice.Spring_React_Application.service.RestaurantService;
import in.spring_react_practice.Spring_React_Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String keyword,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        List<Food> food = foodService.searchFood(keyword);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Long restaurantId,
                                                 @RequestParam boolean isVegetarian,
                                                 @RequestParam boolean isSeasonal,
                                                 @RequestParam boolean isNonVeg,
                                                 @RequestParam(required = false) String foodCategory,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        List<Food> food = foodService.getRestaurantFood(
                restaurantId,
                isVegetarian,
                isSeasonal,
                isNonVeg,
                foodCategory
        );

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}