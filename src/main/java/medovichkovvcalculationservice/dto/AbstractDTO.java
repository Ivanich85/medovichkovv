package medovichkovvcalculationservice.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ivand on 30.09.2020
 */
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractDTO implements Serializable {

    private Long id;
    private String name;
    private BigDecimal cost;
}
