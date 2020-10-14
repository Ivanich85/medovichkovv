package medovichkovvcalculationservice.calculation.service.impl;

import medovichkovvcalculationservice.calculation.service.CalculationService;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author ivand on 30.09.2020
 */
@Service("cake")
public class CakeCalculationService implements CalculationService {

    @Override
    public RecipeDTO calculate(Recipe baseRecipe, BigDecimal recalculationCoef) {
        throw new IllegalStateException("Cake service doesn`t support");
    }
}
