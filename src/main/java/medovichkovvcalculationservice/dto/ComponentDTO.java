package medovichkovvcalculationservice.dto;

import lombok.*;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static medovichkovvcalculationservice.calculation.CalculationUtils.getBigDecimalListSum;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class ComponentDTO extends AbstractDTO {

    private ComponentType type;
    private Integer quantity;
    private List<IngredientDTO> ingredientDTOs = new ArrayList<>();

    public static ComponentDTO createFromComponent(Component component) {
        ComponentDTO componentDTO = new ComponentDTO();
        if (component == null) {
            return componentDTO;
        }
        componentDTO.setName(component.getName());
        componentDTO.setQuantity(component.getQuantity());
        componentDTO.setType(component.getType());

        List<Ingredient> ingredients = component.getIngredients();
        componentDTO.setIngredientDTOs(ingredients.stream()
                .map(IngredientDTO::createFromIngredient)
                .collect(toList()));
        componentDTO.setWeight(getBigDecimalListSum(ingredients.stream()
                .map(Ingredient::getWeight)
                .collect(toList())));
        componentDTO.setCost(getBigDecimalListSum(ingredients.stream()
                .map(Ingredient::getCost)
                .collect(toList())));
        return componentDTO;
    }
}
