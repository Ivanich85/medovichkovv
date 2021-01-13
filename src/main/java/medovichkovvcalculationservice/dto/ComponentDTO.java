package medovichkovvcalculationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import medovichkovvcalculationservice.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivand on 29.09.2020
 */
@ApiModel(description = "Recipe component")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComponentDTO extends AbstractDTO {

    @ApiModelProperty(required = true)
    private ComponentType type;

    @ApiModelProperty(required = true)
    private Integer quantity;

    @ApiModelProperty(required = true)
    private Long recipeDtoId;

    @ApiModelProperty(required = true)
    @JsonProperty("ingredients")
    private List<RecipeIngredientDTO> recipeIngredientDTOS = new ArrayList<>();
}
