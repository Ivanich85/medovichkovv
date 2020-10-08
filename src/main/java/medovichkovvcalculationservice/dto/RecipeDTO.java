package medovichkovvcalculationservice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ivand on 29.09.2020
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RecipeDTO extends AbstractDTO {

    private BigDecimal square;
    private List<ComponentDTO> componentDTOs = new ArrayList<>();
}
