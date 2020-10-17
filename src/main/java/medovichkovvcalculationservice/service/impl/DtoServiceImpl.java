package medovichkovvcalculationservice.service.impl;

import lombok.extern.log4j.Log4j;
import medovichkovvcalculationservice.calculation.CalcServiceFactory;
import medovichkovvcalculationservice.calculation.CalculationUtils;
import medovichkovvcalculationservice.calculation.ServiceType;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.error.CalculationError;
import medovichkovvcalculationservice.service.DtoService;
import medovichkovvcalculationservice.service.RecipeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static medovichkovvcalculationservice.dto.DtoUtils.createFromRecipe;

/**
 * Class for calculation new yummy
 *
 * @author ivand on 06.09.2020
 */
@Service
@Log4j
public class DtoServiceImpl implements DtoService {

    private final RecipeService recipeService;

    public DtoServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer cakes) {
        ServiceType serviceType = checkServiceType(newSquare, cakes);
        Recipe baseRecipe = recipeService.getByIdAndUserWithComponents(baseRecipeId, userId);
        if (baseRecipe == null) {
            throw new IllegalStateException(String.format("Recipe id %s for user %s not found", baseRecipeId, userId));
        }
        var recalcCoef = getRecalculationCoef(baseRecipe, newSquare, cakes);
        return CalcServiceFactory.getCalcService(serviceType).calculate(baseRecipe, recalcCoef);
    }

    @Override
    public RecipeDTO getRecipeDto(Long recipeId, Long userId) {
        return createFromRecipe(recipeService.getByIdAndUser(recipeId, userId));
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

    private BigDecimal getRecalculationCoef(Recipe recalcRecipe, BigDecimal newSquare, Integer cakes) {
        return newSquare != null ?
                CalculationUtils.divide(newSquare, recalcRecipe.getSquare()) :
                CalculationUtils.divide(valueOf(cakes), valueOf(recalcRecipe.getCakes()));
    }
}
