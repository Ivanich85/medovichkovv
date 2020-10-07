package medovichkovvcalculationservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ivand on 30.09.2020
 */
@Getter
@Setter
public abstract class AbstractDTO implements Serializable {
    private String name;
    private BigDecimal weight;
    private BigDecimal cost;
}
