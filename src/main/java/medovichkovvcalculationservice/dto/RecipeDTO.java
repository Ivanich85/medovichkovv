package medovichkovvcalculationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ivand on 29.09.2020
 */
@ApiModel(description = "Model for recipe")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RecipeDTO extends AbstractDTO {

    @ApiModelProperty(required = true)
    private BigDecimal square;

    @ApiModelProperty(required = true)
    private Integer cakes;

    @ApiModelProperty(required = true)
    @JsonProperty("user")
    private Long userId;

    @ApiModelProperty(required = true)
    @JsonProperty("components")
    private List<ComponentDTO> componentDTOs = new ArrayList<>();

    @ApiModelProperty(required = true)
    @JsonProperty("favorite")
    private boolean isFavorite;
}
