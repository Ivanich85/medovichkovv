package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe getByIdAndUser(Long recipeId, Long userId);

    Recipe getByIdAndUserWithComponents(Long recipeId, Long userId);

    List<Recipe> getAllForCurrentUser(Long userId);

    List<Recipe> getAll();

    Recipe save(Recipe recipe);

    boolean delete(Long recipeId, Long userId);

}
