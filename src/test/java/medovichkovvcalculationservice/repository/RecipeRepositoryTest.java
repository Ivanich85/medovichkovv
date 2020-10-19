package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.MedovichkovvCalculationService;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.service.IngredientService;
import medovichkovvcalculationservice.service.RecipeService;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import medovichkovvcalculationservice.testconfiguration.TestDataSourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.USER_ID;
import static medovichkovvcalculationservice.service.TestCalculationDataUtils.createAllIngredients;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedovichkovvCalculationService.class)
@ContextConfiguration(classes = TestDataSourceConfig.class)
class RecipeRepositoryTest {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Test
    void saveRecipe() {
        List<Ingredient> ingredients = createAllIngredients();
        ingredients.forEach(ingredient -> ingredientService.save(ingredient));
        Long userId = USER_ID;
        Recipe recipe = TestCalculationDataUtils.createRecipe();
        List<Ingredient> ingredientsUsedInRecipe = recipe.getComponents().stream()
                .flatMap(
                        c -> c.getRecipeIngredients().stream()
                                .map(RecipeIngredient::getIngredient))
                .collect(Collectors.toList());
        ingredients.forEach(
                ingredient -> {
                    ingredientsUsedInRecipe.stream()
                            .filter(ir -> ingredient.getCost().equals(ir.getCost()))
                            .filter(ir -> ingredient.getName().equals(ir.getName()))
                            .filter(ir -> ingredient.getWeight().equals(ir.getWeight()))
                            .forEach(ir -> ir.setId(ingredient.getId()));
                });
        recipe = recipeService.save(recipe);
        Long recipeId = recipe.getId();

        assertNotNull(recipeId);
        assertTrue(recipeService.delete(recipeId, userId));
        assertNull(recipeService.getByIdAndUser(recipeId, userId));

        // Remove test data
        assertTrue(ingredientService.deleteIngredients(
                ingredientsUsedInRecipe.stream()
                        .map(Ingredient::getId)
                        .collect(Collectors.toList())
        ));
    }
}