package medovichkovvcalculationservice.service;

import java.math.BigDecimal;

/**
 * @author ivand on 30.09.2020
 */
public class TestUtils {

    public static BigDecimal prepareBigDecimal(Double value) {
        return BigDecimal.valueOf(value).setScale(2);
    }
}
