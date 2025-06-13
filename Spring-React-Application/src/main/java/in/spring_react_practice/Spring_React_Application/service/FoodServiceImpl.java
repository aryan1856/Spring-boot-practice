package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.model.Category;
import in.spring_react_practice.Spring_React_Application.model.Food;
import in.spring_react_practice.Spring_React_Application.model.Restaurant;
import in.spring_react_practice.Spring_React_Application.repository.FoodRepository;
import in.spring_react_practice.Spring_React_Application.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setImages(req.getImages());
        food.setIngredientsItems(req.getIngredientsItems());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);

        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId, boolean isVegetarian, boolean isSeasonal, boolean isNonVeg, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegetarian){
            foods = filterByVegetarian(foods, isVegetarian);
        }

        if(isNonVeg)
            foods = filterByNonVegetarian(foods);

        if(isSeasonal)
            foods = filterBySeasonal(foods);

        if(foodCategory!=null && !foodCategory.isEmpty())
            foods = filterByCategory(foods, foodCategory);

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(
                food -> {
                    if(food.getFoodCategory()!=null)
                        return food.getFoodCategory().getName().equals(foodCategory);
                    return false;
                }
        ).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods) {
        return foods.stream().filter(
                Food::isSeasonal
        ).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian(List<Food> foods) {
        return foods.stream().filter(
                food -> !food.isVegetarian()
        ).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream().filter(
                food -> food.isVegetarian()==isVegetarian
        ).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long id) throws Exception {
        Optional<Food> opt = foodRepository.findById(id);
        if(opt.isEmpty())
            throw new Exception("Food doesn't exist");
        return opt.get();
    }

    @Override
    public Food updateAvailability(Long id) throws Exception {
        Food food = findFoodById(id);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
