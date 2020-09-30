package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.calculation.CalcServiceFactory;
import medovichkovvcalculationservice.calculation.ServiceType;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Class for calculation new yummy
 *
 * @author ivand on 06.09.2020
 */
@Service
public class RecalculationServiceImpl implements RecalculationService {

    private final RecipeService recipeService;

    public RecalculationServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public RecipeDTO calculateRecipeBySquare(Long baseRecipeId, Long userId, BigDecimal newSquare) {
        Recipe baseRecipe = recipeService.getByIdAndUser(baseRecipeId, userId);
        var recalcCoef = newSquare != null ? newSquare.divide(baseRecipe.getSquare()).setScale(3, RoundingMode.HALF_UP) : null;
        RecipeDTO recalcRecipe = CalcServiceFactory.getCalcService(ServiceType.SQUARE).calculate(baseRecipe, recalcCoef);
        return recalcRecipe;
    }

    @Override
    public RecipeDTO calculateRecipeByLayer(Long baseRecipeId, Long userId, Integer layers) {
        throw new IllegalStateException("Method doesn`t support");
    }
}
