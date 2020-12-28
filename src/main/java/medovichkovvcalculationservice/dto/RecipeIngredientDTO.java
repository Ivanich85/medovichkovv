package medovichkovvcalculationservice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@EqualsAndHashCode
public class RecipeIngredientDTO {

    private Long id;
    private Long componentId;
    private IngredientDTO ingredientDTO;
    private BigDecimal quantity;
}
