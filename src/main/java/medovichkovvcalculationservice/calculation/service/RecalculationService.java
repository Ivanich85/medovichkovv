package medovichkovvcalculationservice.calculation.service;

import medovichkovvcalculationservice.dto.RecipeDTO;

import java.math.BigDecimal;

public interface RecalculationService {

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param newSquare square what we need for calculated cake
     * @param layers layers for new cake
     *
     * @return recipe of cake with expected square
     */
    RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer layers);
}
