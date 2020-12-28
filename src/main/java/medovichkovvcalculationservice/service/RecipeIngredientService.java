package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientService {

    RecipeIngredient getById(Long recipeIngredientId);

    RecipeIngredient save(RecipeIngredient ingredient);

    List<RecipeIngredient> getByComponentId(Long componentId);

    void delete(Long ingredientId);

    void deleteAllForComponent(Long componentId);

    void deleteAllForRecipe(Long recipeId);
}
