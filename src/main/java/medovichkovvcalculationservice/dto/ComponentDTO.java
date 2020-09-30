package medovichkovvcalculationservice.dto;

import lombok.*;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.enums.ComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<IngredientDTO> ingredientDTOs = new ArrayList<>();

    public static ComponentDTO createFromComponent(Component component) {
        ComponentDTO componentDTO = new ComponentDTO();
        if (component == null) {
            return componentDTO;
        }
        componentDTO.setName(component.getName());
        componentDTO.setQuantity(component.getQuantity());
        componentDTO.setType(component.getType());
        componentDTO.setIngredientDTOs(
                component.getIngredients().stream()
                        .map(IngredientDTO::createFromIngredient)
                        .collect(Collectors.toList()));
        return componentDTO;
    }
}
