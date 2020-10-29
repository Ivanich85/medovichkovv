package medovichkovvcalculationservice.calculation.service;

import lombok.SneakyThrows;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.exception.CalculationException;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CalculationServiceTest {

    @Autowired
    @Qualifier("square")
    private CalculationService squareService;

    @Test
    void calculateNullRecipe() {
        assertThrows(CalculationException.class,
                () -> squareService.calculate(TestCalculationDataUtils.createRecipe(), null),
                "Recalculation coefficient can`t be null");
    }

    @Test
    void calculateNullRecalcCoef() {
        assertThrows(CalculationException.class,
                () -> squareService.calculate(TestCalculationDataUtils.createRecipe(), null),
                "Base recipe can`t be null");
    }

    @Test
    void checkNewRecipeDtoSum_0_00() throws DtoCreateException {
        RecipeDTO actualDTO = squareService.calculate(TestCalculationDataUtils.createRecipe(), BigDecimal.valueOf(0));
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        expectedDTO.setCost(BigDecimal.valueOf(0));
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "componentDTOs");
    }

    @Test
    void checkNewRecipeDtoSum_0_45() throws DtoCreateException {
        RecipeDTO actualDTO = squareService.calculate(TestCalculationDataUtils.createRecipe(), BigDecimal.valueOf(0.45));
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        expectedDTO.setCost(BigDecimal.valueOf(86.68));
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "componentDTOs");
    }

    @Test
    void checkNewRecipeDtoSum_1_30() throws DtoCreateException {
        RecipeDTO actualDTO = squareService.calculate(TestCalculationDataUtils.createRecipe(), BigDecimal.valueOf(1.3));
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        expectedDTO.setCost(BigDecimal.valueOf(250.4));
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "componentDTOs");
    }

    @Test
    void checkNewRecipeDtoSum_1_70() throws DtoCreateException {
        RecipeDTO actualDTO = squareService.calculate(TestCalculationDataUtils.createRecipe(), BigDecimal.valueOf(1.7));
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        expectedDTO.setCost(BigDecimal.valueOf(327.4));
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "componentDTOs");
    }
}