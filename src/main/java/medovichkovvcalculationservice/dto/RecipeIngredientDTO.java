package medovichkovvcalculationservice.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author ivand on 29.09.2020
 */
@ApiModel(description = "Model for recipe`s ingredient")
@Getter
@Setter
@EqualsAndHashCode
public class RecipeIngredientDTO {

    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private Long componentId;

    @ApiModelProperty(required = true)
    private IngredientDTO ingredientDTO;

    @ApiModelProperty(required = true)
    private BigDecimal quantity;
}
