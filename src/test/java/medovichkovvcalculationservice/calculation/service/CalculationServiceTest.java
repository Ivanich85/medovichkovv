package medovichkovvcalculationservice.calculation.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.error.CalculationError;
import medovichkovvcalculationservice.service.AbstractTest;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculationServiceTest extends AbstractTest {

    @Autowired
    @Qualifier("square")
    private CalculationService squareService;

    @Test
    void calculateNullRecipe() {
        assertThat(new RecipeDTO())
                .isEqualTo(squareService.calculate(null, BigDecimal.ONE));
    }

    @Test
    void calculateNullRecalcCoef() {
        assertThrows(CalculationError.class,
                () -> squareService.calculate(TestCalculationDataUtils.createRecipe(), null));
    }

    @Test
    void calculate() {
        RecipeDTO actualDTO = squareService.calculate(TestCalculationDataUtils.createRecipe(), BigDecimal.valueOf(2));
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        assertThat(actualDTO).isEqualTo(expectedDTO);
    }
}