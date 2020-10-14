package medovichkovvcalculationservice.dto;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.service.AbstractTest;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import org.junit.jupiter.api.Test;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.EntityNumber.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DtoUtilsTest extends AbstractTest {

    @Test
    void createFromIngredient() {
        Ingredient expectedIngredient = TestCalculationDataUtils.createIngredient(ONE);
        IngredientDTO expectedDTO = TestCalculationDataUtils.createIngredientDTO();
        IngredientDTO actualDTO = DtoUtils.createFromIngredient(expectedIngredient);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void createFromComponent() {
        Component expectedComponent = TestCalculationDataUtils.createComponent(ONE);
        ComponentDTO expectedDTO = TestCalculationDataUtils.createComponentDTO();
        ComponentDTO actualDTO = DtoUtils.createFromComponent(expectedComponent);
        assertEquals(2, actualDTO.getIngredientDTOs().size());
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "ingredientDTOs");
    }

    @Test
    void createFromRecipe() {
        Recipe expectedRecipe = TestCalculationDataUtils.createRecipe();
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        RecipeDTO actualDTO = DtoUtils.createFromRecipe(expectedRecipe);
        assertThat(actualDTO).isEqualTo(expectedDTO);
    }
}