package medovichkovvcalculationservice.integrtest;

import medovichkovvcalculationservice.MedovichkovvCalculationService;
import medovichkovvcalculationservice.TestDataUtils;
import medovichkovvcalculationservice.dto.DtoUtils;
import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.service.IngredientService;
import medovichkovvcalculationservice.service.RecipeService;
import medovichkovvcalculationservice.testconfiguration.TestDataSourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.TestDataUtils.USER_ID;
import static medovichkovvcalculationservice.TestDataUtils.createAllIngredients;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MedovichkovvCalculationService.class)
@ContextConfiguration(classes = TestDataSourceConfig.class)
class CRUDRecipeTest {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @Test
    void saveAndDeleteRecipe() {
        List<Ingredient> ingredients = createAllIngredients();
        ingredients.forEach(ingredient -> ingredientService.save(ingredient));
        Long userId = USER_ID;
        Recipe recipe = TestDataUtils.createRecipe();
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
        recipeService.delete(recipeId, userId);
        assertNull(recipeService.getByIdAndUser(recipeId, userId));

        ingredientService.deleteIngredients(
                ingredientsUsedInRecipe.stream()
                        .map(Ingredient::getId)
                        .collect(Collectors.toList()
        ));
    }

    @Test
    void saveAndDeleteIngredientFromDTO() {
        List<IngredientDTO> ingredientDtos = createAllIngredients().stream()
                .map(DtoUtils::createFromIngredient)
                .collect(Collectors.toList());
        ingredientDtos = ingredientDtos.stream()
                .map(i -> ingredientService.save(i))
                .collect(Collectors.toList());
        List<Long> ingredientIds = ingredientDtos.stream()
                .map(IngredientDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        assertEquals(ingredientDtos.size(), ingredientIds.size());
        ingredientIds.forEach(id -> ingredientService.delete(id));
    }
}