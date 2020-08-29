package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient getById(Long ingredientId);

    List<Ingredient> getByComponentId(Long componentId);

    Ingredient save(Ingredient ingredient);

    boolean delete(Long ingredientId);

    boolean deleteAllForComponent(Long componentId);

    boolean deleteAllForRecipe(Long recipeId);
}
