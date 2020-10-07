package medovichkovvcalculationservice.calculation.service.impl;

import lombok.extern.log4j.Log4j;
import medovichkovvcalculationservice.calculation.CalcServiceFactory;
import medovichkovvcalculationservice.calculation.CalculationUtils;
import medovichkovvcalculationservice.calculation.ServiceType;
import medovichkovvcalculationservice.calculation.service.RecalculationService;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.error.CalculationError;
import medovichkovvcalculationservice.service.RecipeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

/**
 * Class for calculation new yummy
 *
 * @author ivand on 06.09.2020
 */
@Service
@Log4j
public class RecalculationServiceImpl implements RecalculationService {

    private final RecipeService recipeService;

    public RecalculationServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer layers) {
        ServiceType serviceType = checkServiceType(newSquare, layers);
        Recipe recalcRecipe = recipeService.getByIdAndUser(baseRecipeId, userId);
        if (recalcRecipe == null) {
            throw new IllegalStateException(String.format("Recipe id %s for user %s not found", baseRecipeId, userId));
        }
        var recalcCoef = getRecalculationCoef(recalcRecipe, newSquare, layers);
        return CalcServiceFactory.getCalcService(serviceType).calculate(recalcRecipe, recalcCoef);
    }

    private ServiceType checkServiceType(BigDecimal newSquare, Integer cakes) {
        if (newSquare == null && cakes == null) {
            throw CalculationError.create("New recipe square and cakes can`t be null");
        }
        if (newSquare != null && cakes != null) {
            throw CalculationError.create("New recipe square and cakes not null. It`s ambiguity");
        }
        return newSquare != null ? ServiceType.SQUARE : ServiceType.CAKE;
    }

    private BigDecimal getRecalculationCoef(Recipe recalcRecipe, BigDecimal newSquare, Integer layers) {
        return newSquare != null ?
                CalculationUtils.divide(newSquare, recalcRecipe.getSquare(), 3) :
                CalculationUtils.divide(valueOf(layers), valueOf(recalcRecipe.getCakes()), 3);
    }
}
