package medovichkovvcalculationservice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import medovichkovvcalculationservice.enums.IngredientQtyType;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class IngredientDTO extends AbstractDTO {

    private IngredientQtyType type;

}
