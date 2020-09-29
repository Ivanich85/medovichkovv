package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;

import java.math.BigDecimal;

public interface CalculationService {

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param expectedSquare square what we need for calculated cake
     * @return recipe of cake with expected square
     */
    RecipeDTO calculateRecipe(Long baseRecipeId, Long userId, BigDecimal expectedSquare);
}
