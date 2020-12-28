package medovichkovvcalculationservice.dto;

import lombok.*;
import medovichkovvcalculationservice.enums.IngredientQtyType;

import java.math.BigDecimal;

/**
 * @author ivand on 23.11.2020
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class IngredientDTO extends AbstractDTO {

    private Long userId;
    private BigDecimal weight;
    private IngredientQtyType type;
}
