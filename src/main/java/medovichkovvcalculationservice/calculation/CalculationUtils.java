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
        if (first == null || second == null) {
            return BigDecimal.ZERO;
        }
        return first.multiply(second).round(new MathContext(precision, RoundingMode.HALF_UP));
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        if (dividend == null || dividend.equals(BigDecimal.ZERO) || divisor == null || divisor.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, 3, RoundingMode.HALF_UP);
    }

    public static BigDecimal getListSum(List<BigDecimal> bigDecimals) {
        if (CollectionUtils.isEmpty(bigDecimals)) {
            return BigDecimal.ZERO;
        }
        return bigDecimals.stream().reduce((bd1, bd2) -> bd1.add(bd2)).orElse(BigDecimal.ZERO);
    }

}
