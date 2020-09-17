package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Recipe;

import java.util.List;

public interface RecipeService extends CrudService<Recipe, Long> {

    Recipe getByIdAndUser(Long recipeId, Long userId);

    Recipe getByIdAndUserWithComponents(Long recipeId, Long userId);

    List<Recipe> getAllForCurrentUser(Long userId);

    List<Recipe> getAll();

    boolean delete(Long recipeId, Long userId);

}
