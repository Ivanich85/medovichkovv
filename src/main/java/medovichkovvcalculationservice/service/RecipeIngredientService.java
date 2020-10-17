package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientService extends CrudService<RecipeIngredient, Long> {

    List<RecipeIngredient> getByComponentId(Long componentId);

    boolean deleteAllForComponent(Long componentId);

    boolean deleteAllForRecipe(Long recipeId);
}
