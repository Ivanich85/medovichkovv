package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;

import java.math.BigDecimal;

public interface DtoService {

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param newSquare square what we need for calculated recipe
     * @param cakes cakes for new recipe
     *
     * @return recipe of cake with expected square
     */
    RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer cakes);

    /**
     * @param recipeId recipe what we need
     * @param userId recipe`s owner
     *
     * @return recipe main description details
     */
    RecipeDTO getRecipeDto(Long recipeId, Long userId);
}
