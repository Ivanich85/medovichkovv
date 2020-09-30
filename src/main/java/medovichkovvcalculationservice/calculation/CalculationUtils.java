package medovichkovvcalculationservice.calculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ivand on 01.10.2020
 */
public abstract class CalculationUtils {

    private static BigDecimal multiply(BigDecimal base, BigDecimal recalcCoef, int precision) {
        return base.multiply(recalcCoef, new MathContext(precision, RoundingMode.HALF_UP));
    }
}
