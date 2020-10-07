package medovichkovvcalculationservice.calculation;

import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author ivand on 01.10.2020
 */
public abstract class CalculationUtils {

    public static BigDecimal multiply(BigDecimal first, BigDecimal second, int precision) {
        return first.multiply(second, new MathContext(precision, RoundingMode.HALF_UP));
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int precision) {
        if (dividend == null || divisor == null) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor).setScale(precision, RoundingMode.HALF_UP);
    }

    public static BigDecimal getBigDecimalListSum(List<BigDecimal> bigDecimals) {
        if (CollectionUtils.isEmpty(bigDecimals)) {
            return BigDecimal.ZERO;
        }
        return bigDecimals.stream().reduce((bd1, bd2) -> bd1.add(bd2)).orElse(BigDecimal.ZERO);
    }

}
