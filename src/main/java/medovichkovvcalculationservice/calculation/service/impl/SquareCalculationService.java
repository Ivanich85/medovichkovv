package medovichkovvcalculationservice.calculation.service.impl;

import medovichkovvcalculationservice.calculation.service.CalculationService;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.error.CalculationError;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static medovichkovvcalculationservice.dto.DtoUtils.createFromRecipe;

/**
 * @author ivand on 30.09.2020
 */
@Service("square")
public class SquareCalculationService implements CalculationService {

    @Override
    public RecipeDTO calculate(Recipe baseRecipe, BigDecimal recalculationCoef) {
        if (recalculationCoef == null) {
            throw CalculationError.create("Recalculation coefficient can`t be null");
        }
        RecipeDTO recipeDTO = createFromRecipe(baseRecipe);
        return recipeDTO;
    }
}
