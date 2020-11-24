package medovichkovvcalculationservice.calculation.service.impl;

import medovichkovvcalculationservice.calculation.service.CalculationService;
import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.dto.RecipeIngredientDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.exception.CalculationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.calculation.CalculationUtils.getListSum;
import static medovichkovvcalculationservice.calculation.CalculationUtils.multiply;
import static medovichkovvcalculationservice.dto.DtoUtils.createFromRecipeWithSumAndComponents;

/**
 * @author ivand on 30.09.2020
 */
@Service("square")
public class SquareCalculationService implements CalculationService {

    @Override
    public RecipeDTO calculate(Recipe baseRecipe, BigDecimal recalculationCoef) {
        if (recalculationCoef == null) {
            throw new CalculationException("Recalculation coefficient can`t be null");
        }
        if (baseRecipe == null) {
            throw new CalculationException("Base recipe can`t be null");
        }
        RecipeDTO recipeDTO = createFromRecipeWithSumAndComponents(baseRecipe);
        List<ComponentDTO> componentDTOs = recipeDTO.getComponentDTOs();
        componentDTOs.stream()
                .flatMap(componentDTO -> componentDTO.getRecipeIngredientDTOS().stream())
                .forEach(ingredientDTO -> {
                    ingredientDTO.setCost(multiply(ingredientDTO.getCost(), recalculationCoef));
                    ingredientDTO.setWeight(multiply(ingredientDTO.getWeight(), recalculationCoef));
                });
        componentDTOs.stream()
                .forEach(c -> c.setCost(getListSum(
                        c.getRecipeIngredientDTOS().stream()
                                .map(RecipeIngredientDTO::getCost)
                                .collect(Collectors.toList()))));
        recipeDTO.setCost(getListSum(
                componentDTOs.stream()
                        .map(ComponentDTO::getCost)
                        .collect(Collectors.toList())));
        return recipeDTO;
    }
}
