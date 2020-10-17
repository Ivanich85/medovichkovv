package medovichkovvcalculationservice.calculation.service.impl;

import medovichkovvcalculationservice.calculation.service.CalculationService;
import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.dto.RecipeIngredientDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.error.CalculationError;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.calculation.CalculationUtils.getListSum;
import static medovichkovvcalculationservice.calculation.CalculationUtils.multiply;
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
        if (baseRecipe == null) {
            throw CalculationError.create("Base recipe can`t be null");
        }
        RecipeDTO recipeDTO = createFromRecipe(baseRecipe);
        recipeDTO.getComponentDTOs().stream()
                .flatMap(componentDTO -> componentDTO.getRecipeIngredientDTOS().stream())
                .forEach(ingredientDTO -> {
                    ingredientDTO.setCost(multiply(ingredientDTO.getCost(), recalculationCoef));
                    ingredientDTO.setWeight(multiply(ingredientDTO.getWeight(), recalculationCoef));
                });
        recipeDTO.getComponentDTOs().stream()
                .forEach(c -> c.setCost(getListSum(
                        c.getRecipeIngredientDTOS().stream()
                                .map(RecipeIngredientDTO::getCost)
                                .collect(Collectors.toList()))));
        recipeDTO.setCost(getListSum(
                recipeDTO.getComponentDTOs().stream()
                        .map(ComponentDTO::getCost)
                        .collect(Collectors.toList())));
        return recipeDTO;
    }
}
