package medovichkovvcalculationservice.dto;

import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.service.AbstractTest;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import org.junit.jupiter.api.Test;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.EntityNumber.ONE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoUtilsTest extends AbstractTest {

    @Test
    void createFromRecipe() {


    }

    @Test
    void createFromComponent() {
    }

    @Test
    void createFromIngredient() {
        Ingredient expectedIngredient = TestCalculationDataUtils.createIngredient(ONE);
        IngredientDTO expectedDTO = TestCalculationDataUtils.createIngredientDTO();
        IngredientDTO actualDTO = DtoUtils.createFromIngredient(expectedIngredient);
        assertEquals(expectedDTO, actualDTO);
    }
}