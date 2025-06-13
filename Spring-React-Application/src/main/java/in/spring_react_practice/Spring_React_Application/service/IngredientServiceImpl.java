package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.model.IngredientCategory;
import in.spring_react_practice.Spring_React_Application.model.IngredientsItem;
import in.spring_react_practice.Spring_React_Application.model.Restaurant;
import in.spring_react_practice.Spring_React_Application.repository.IngredientCategoryRepository;
import in.spring_react_practice.Spring_React_Application.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService{

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setName(name);
        category.setRestaurant(restaurant);
        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);
        if(opt.isEmpty())
            throw new Exception("Ingredient category not found");
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long id) {
        return ingredientItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String name, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);
        IngredientsItem ingredientsItem = new IngredientsItem();
        ingredientsItem.setName(name);
        ingredientsItem.setRestaurant(restaurant);
        ingredientsItem.setCategory(category);
        IngredientsItem savedItem =  ingredientItemRepository.save(ingredientsItem);
        category.getIngredients().add(savedItem);
        return savedItem;
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> item = ingredientItemRepository.findById(id);
        if(item.isEmpty())
            throw new Exception("Ingredient not found");
        IngredientsItem ingredient = item.get();
        ingredient.setInStock(!ingredient.isInStock());
        return ingredientItemRepository.save(ingredient);
    }
}
