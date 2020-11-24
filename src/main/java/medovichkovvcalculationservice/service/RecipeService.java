package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;

import java.math.BigDecimal;
import java.util.List;

public interface RecipeService extends CrudService<Recipe, Long> {

    Recipe getByIdAndUser(Long recipeId, Long userId);

    Recipe getByIdAndUserWithComponents(Long recipeId, Long userId);

    List<Recipe> getAllForCurrentUser(Long userId);

    List<Recipe> getAll();

    boolean delete(Long recipeId, Long userId);

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param newSquare square what we need for calculated recipe
     * @param cakes cakes for new recipe
     *
     * @return recipe of cake with expected square
     */
    RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer cakes);

    List<RecipeDTO> getAllRecipesForUser(Long userId);

    RecipeDTO getRecipeForUser(Long recipeId, Long userId);

}
