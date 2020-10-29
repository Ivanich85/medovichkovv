package medovichkovvcalculationservice.calculation.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.exception.DtoCreateException;

import java.math.BigDecimal;

public interface CalculationService {
    RecipeDTO calculate(Recipe baseRecipe, BigDecimal recalculationCoef) throws DtoCreateException;
}
