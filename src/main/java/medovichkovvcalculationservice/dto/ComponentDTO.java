package medovichkovvcalculationservice.dto;

import lombok.*;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class ComponentDTO extends AbstractDTO {

    private ComponentType type;
    private Integer quantity;
    private List<IngredientDTO> ingredients = new ArrayList<>();

    public static ComponentDTO createFromComponentNoIngredients(Component component) {
        ComponentDTO componentDTO = new ComponentDTO();
        if (component == null) {
            return componentDTO;
        }
        componentDTO.setName(component.getName());
        componentDTO.setQuantity(component.getQuantity());
        componentDTO.setType(component.getType());
        return componentDTO;
    }

}
