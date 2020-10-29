package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.exception.DtoCreateException;

import java.math.BigDecimal;
import java.util.List;

public interface DtoService {

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param newSquare square what we need for calculated recipe
     * @param cakes cakes for new recipe
     *
     * @return recipe of cake with expected square
     */
    RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer cakes) throws DtoCreateException;

    List<RecipeDTO> getAllRecipesForUser(Long userId) throws DtoCreateException;

    RecipeDTO getRecipeForUser(Long recipeId, Long userId) throws DtoCreateException;
}
