package medovichkovvcalculationservice.dto;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.EntityNumber.ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DtoUtilsTest {

    @Test
    void createFromIngredient() {
        RecipeIngredient expectedIngredient = TestCalculationDataUtils.createRecipeIngredient(ONE);
        RecipeIngredientDTO expectedDTO = TestCalculationDataUtils.createIngredientDTO();
        RecipeIngredientDTO actualDTO = DtoUtils.createFromRecipeIngredient(expectedIngredient);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void createFromComponent() {
        Component expectedComponent = TestCalculationDataUtils.createComponent(ONE);
        ComponentDTO expectedDTO = TestCalculationDataUtils.createComponentDTO();
        ComponentDTO actualDTO = DtoUtils.createFromComponent(expectedComponent);
        assertEquals(2, actualDTO.getRecipeIngredientDTOS().size());
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "recipeIngredientDTOS");
    }

    @Test
    void createFromRecipe() {
        Recipe expectedRecipe = TestCalculationDataUtils.createRecipe();
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        RecipeDTO actualDTO = DtoUtils.createFromRecipe(expectedRecipe);
        assertThat(actualDTO).isEqualTo(expectedDTO);
    }

    @Test
    void createFromRecipeNull() {
        assertThrows(IllegalStateException.class,
                () -> DtoUtils.createFromRecipe(null),
                "Recipe can not be null");
    }
}