package in.spring_react_practice.Spring_React_Application.service;

import in.spring_react_practice.Spring_React_Application.model.IngredientCategory;
import in.spring_react_practice.Spring_React_Application.model.IngredientsItem;

import java.util.List;

public interface IngredientsService {

    IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    List<IngredientsItem> findRestaurantIngredients(Long id);

    IngredientsItem createIngredientItem(Long restaurantId, String name, Long categoryId) throws Exception;

    IngredientsItem updateStock(Long id) throws Exception;

}