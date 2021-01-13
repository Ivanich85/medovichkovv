package medovichkovvcalculationservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import medovichkovvcalculationservice.enums.IngredientQtyType;

import java.math.BigDecimal;

/**
 * @author ivand on 23.11.2020
 */

@ApiModel(description = "Model for common ingredient")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class IngredientDTO extends AbstractDTO {

    @ApiModelProperty(required = true)
    private Long userId;

    @ApiModelProperty(required = true)
    private BigDecimal weight;

    @ApiModelProperty(required = true)
    private IngredientQtyType type;
}
