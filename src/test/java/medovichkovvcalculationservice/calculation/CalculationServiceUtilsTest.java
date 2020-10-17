package medovichkovvcalculationservice.calculation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CalculationServiceUtilsTest {

    @Test
    void multiply() {
        assertThat(BigDecimal.valueOf(15.20))
                .isEqualTo(CalculationUtils.multiply(BigDecimal.valueOf(5), BigDecimal.valueOf(3.04)));
    }

    @Test
    void multiplyRoundUp() {
        assertThat(BigDecimal.valueOf(15.10))
                .isEqualTo(CalculationUtils.multiply(BigDecimal.valueOf(5), BigDecimal.valueOf(3.01)));
    }

    @Test
    void multiplyRoundDown() {
        assertThat(BigDecimal.valueOf(12.00))
                .isEqualTo(CalculationUtils.multiply(BigDecimal.valueOf(4), BigDecimal.valueOf(3.01)));
    }

    @Test
    void multiplyNull() {
        assertThat(BigDecimal.ZERO)
                .isEqualTo(CalculationUtils.multiply(null, BigDecimal.valueOf(3.04)));
        assertThat(BigDecimal.ZERO)
                .isEqualTo(CalculationUtils.multiply(BigDecimal.valueOf(3.04), null));
    }

    @Test
    void divideNullOrZero() {
        assertThat(BigDecimal.ZERO)
                .isEqualTo(CalculationUtils.divide(null, BigDecimal.valueOf(3.04)));
        assertThat(BigDecimal.ZERO)
                .isEqualTo(CalculationUtils.divide(BigDecimal.ZERO, BigDecimal.valueOf(3.04)));
    }

    @Test
    void divideToNullOrZero() {
        assertThat(BigDecimal.ZERO)
                .isEqualTo(CalculationUtils.divide(BigDecimal.valueOf(3.04), null));
        assertThat(BigDecimal.ZERO)
                .isEqualTo(CalculationUtils.divide(BigDecimal.valueOf(3.04), BigDecimal.ZERO));
    }

    @Test
    void divide() {
        assertThat(BigDecimal.valueOf(2.123))
                .isEqualTo(CalculationUtils.divide(BigDecimal.valueOf(6.369), BigDecimal.valueOf(3)));
    }

    @Test
    void divideRoundUp() {
        assertThat(BigDecimal.valueOf(2.117))
                .isEqualTo(CalculationUtils.divide(BigDecimal.valueOf(6.350), BigDecimal.valueOf(3)));
    }

    @Test
    void divideRoundDown() {
        assertThat(BigDecimal.valueOf(2.113))
                .isEqualTo(CalculationUtils.divide(BigDecimal.valueOf(6.340), BigDecimal.valueOf(3)));
    }

    @Test
    void sumEmptyList() {
        assertThat(BigDecimal.ZERO).isEqualTo(CalculationUtils.getListSum(List.of()));
    }

    @Test
    void getBigDecimalListSum() {
        var list = List.of(BigDecimal.ONE,
                BigDecimal.valueOf(2), BigDecimal.ZERO, BigDecimal.valueOf(5.2), BigDecimal.valueOf(1.03));
        assertThat(BigDecimal.valueOf(9.23)).isEqualTo(CalculationUtils.getListSum(list));
    }
}