package medovichkovvcalculationservice.dto;

import lombok.*;
import medovichkovvcalculationservice.entity.Recipe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static medovichkovvcalculationservice.calculation.CalculationUtils.getBigDecimalListSum;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class RecipeDTO extends AbstractDTO {

    private BigDecimal square;
    private List<ComponentDTO> componentDTOs = new ArrayList<>();

    public static RecipeDTO createFromRecipe(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        if (recipe == null) {
            return recipeDTO;
        }
        recipeDTO.setName(recipe.getName());
        recipeDTO.setSquare(recipe.getSquare());
        recipeDTO.setComponentDTOs(recipe.getComponents().stream()
                .map(ComponentDTO::createFromComponent)
                .collect(Collectors.toList()));
        var componentDTOs = recipeDTO.getComponentDTOs();
        recipeDTO.setWeight(getBigDecimalListSum(componentDTOs.stream()
                .map(ComponentDTO::getWeight)
                .collect(toList())));
        recipeDTO.setCost(getBigDecimalListSum(componentDTOs.stream()
                .map(ComponentDTO::getCost)
                .collect(toList())));
        return recipeDTO;
    }
}
