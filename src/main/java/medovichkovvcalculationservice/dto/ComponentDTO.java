package medovichkovvcalculationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import medovichkovvcalculationservice.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ComponentDTO extends AbstractDTO {

    private ComponentType type;
    private Integer quantity;
    private Long recipeDtoId;

    @JsonProperty("ingredients")
    private List<RecipeIngredientDTO> recipeIngredientDTOS = new ArrayList<>();
}
