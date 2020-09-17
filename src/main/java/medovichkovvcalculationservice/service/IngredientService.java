package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Ingredient;

import java.util.List;

public interface IngredientService extends CrudService<Ingredient, Long> {

    List<Ingredient> getByComponentId(Long componentId);

    boolean deleteAllForComponent(Long componentId);

    boolean deleteAllForRecipe(Long recipeId);
}
