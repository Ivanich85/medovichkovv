package medovichkovvcalculationservice.dto;

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
    private List<IngredientDTO> ingredientDTOs = new ArrayList<>();
}
