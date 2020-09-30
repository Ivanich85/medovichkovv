package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;

import java.math.BigDecimal;

public interface RecalculationService {

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param newSquare square what we need for calculated cake
     * @return recipe of cake with expected square
     */
    RecipeDTO calculateRecipeBySquare(Long baseRecipeId, Long userId, BigDecimal newSquare);

    /**
     * @param baseRecipeId recipe that we used as base for calculation
     * @param userId current user id
     * @param layers layers quantity what we need for calculated cake
     * @return recipe of cake with expected layers
     */
    RecipeDTO calculateRecipeByLayer(Long baseRecipeId, Long userId, Integer layers);
}
