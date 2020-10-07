package medovichkovvcalculationservice.dto;

import lombok.*;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.enums.IngredientQtyType;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class IngredientDTO extends AbstractDTO {

    private IngredientQtyType type;

    public static IngredientDTO createFromIngredient(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        if (ingredient == null) {
            return ingredientDTO;
        }
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setType(ingredient.getType());
        ingredientDTO.setCost(ingredient.getCost());
        ingredientDTO.setWeight(ingredient.getWeight());
        return ingredientDTO;
    }

}
