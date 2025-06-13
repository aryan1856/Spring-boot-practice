package in.spring_react_practice.Spring_React_Application.request;

import in.spring_react_practice.Spring_React_Application.model.Category;
import in.spring_react_practice.Spring_React_Application.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean isVegetarian;
    private boolean isSeasonal;
    private List<IngredientsItem> ingredients;
}