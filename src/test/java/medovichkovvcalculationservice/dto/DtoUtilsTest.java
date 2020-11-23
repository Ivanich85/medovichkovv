package medovichkovvcalculationservice.dto;

import medovichkovvcalculationservice.TestDataUtils;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.exception.EntityCreateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static medovichkovvcalculationservice.TestDataUtils.EntityNumber.ONE;
import static medovichkovvcalculationservice.TestDataUtils.createIngredient;
import static medovichkovvcalculationservice.TestDataUtils.createIngredientDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DtoUtilsTest {

    @Test
    void createFromIngredient() {
        RecipeIngredient expectedIngredient = TestDataUtils.createRecipeIngredient(ONE);
        RecipeIngredientDTO expectedDTO = TestDataUtils.createRecipeIngredientDTO();
        RecipeIngredientDTO actualDTO = DtoUtils.createFromRecipeIngredient(expectedIngredient);
        assertEquals(expectedDTO, actualDTO);
    }

    @Test
    void createFromComponent() {
        Component expectedComponent = TestDataUtils.createComponent(ONE);
        ComponentDTO expectedDTO = TestDataUtils.createComponentDTO();
        ComponentDTO actualDTO = DtoUtils.createFromComponent(expectedComponent);
        assertEquals(2, actualDTO.getRecipeIngredientDTOS().size());
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "recipeIngredientDTOS");
    }

    @Test
    void createFromRecipe() throws DtoCreateException {
        Recipe expectedRecipe = TestDataUtils.createRecipe();
        RecipeDTO expectedDTO = TestDataUtils.createRecipeDTO();
        RecipeDTO actualDTO = DtoUtils.createFromRecipeWithSumAndComponents(expectedRecipe);
        assertThat(actualDTO).isEqualTo(expectedDTO);
    }

    @Test
    void createFromRecipeNull() {
        assertThrows(DtoCreateException.class,
                () -> DtoUtils.createFromRecipeWithSumAndComponents(null),
                "Recipe can not be null");
    }

    @Test
    void testCreateFromIngredientNull() {
        assertThrows(DtoCreateException.class,
                () -> DtoUtils.createFromIngredient(null),
                "Ingredient can`t be null");
    }

    @Test
    void testCreateFromIngredient() throws DtoCreateException {
        Ingredient ingredient = createIngredient();
        IngredientDTO expected = createIngredientDTO();
        assertEquals(expected, DtoUtils.createFromIngredient(ingredient));
    }

    @Test
    void createToIngredientNull() {
        assertThrows(EntityCreateException.class,
                () -> DtoUtils.createToIngredient(null),
                "Ingredient DTO can`t be null");
    }

    @Test
    void createToIngredient() throws EntityCreateException {
        IngredientDTO ingredientDTO = createIngredientDTO();
        Ingredient expected = createIngredient();
        assertEquals(expected, DtoUtils.createToIngredient(ingredientDTO));
    }
}