package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.enums.IngredientQtyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.service.TestUtils.prepareBigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class IngredientServiceTest extends AbstractTest {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ComponentService componentService;

    @Test
    public void getById() {
        var expected =
                new Ingredient(ingredientId, null, "Яйца", prepareBigDecimal(10.0), IngredientQtyType.PIECE, prepareBigDecimal(75.0));
        var actual = ingredientService.getById(ingredientId);
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "component");
    }

    @Test
    public void getByComponentId() {
        List<Long> expectedIngredientIds = List.of(ingredientId, 10001L, 10002L, 10003L, 10004L, 10005L);
        List<Long> actualIngredientIds = getAllIdsByComponentId(componentId);
        assertThat(actualIngredientIds).isEqualTo(expectedIngredientIds);
    }

    @Test
    public void saveNew() {
        var expected =
                ingredientService.save(new Ingredient(ingredientId, componentService.getById(componentId), "Яйца", prepareBigDecimal(10.0), IngredientQtyType.PIECE, prepareBigDecimal(75.0)));
        var actual = ingredientService.getById(expected.getId());
        assertThat(actual).
                isEqualToIgnoringGivenFields(expected, "component");
    }

    @Test
    public void updateExist() {
        String newName = "Шоколад";
        IngredientQtyType type = IngredientQtyType.GRAM;
        var expectedIngredient =
                new Ingredient(ingredientId, componentService.getById(componentId), newName, prepareBigDecimal(10.0), type, prepareBigDecimal(75.0));
        var actualIngredient = ingredientService.getById(ingredientId);
        actualIngredient.setType(type);
        actualIngredient.setName(newName);
        assertThat(ingredientService.save(actualIngredient))
                .isEqualToIgnoringGivenFields(expectedIngredient, "component");
    }

    @Test
    public void delete() {
        Ingredient deleted = ingredientService.getById(ingredientId);
        assertThat(deleted).isNotNull();
        assertThat(ingredientService.delete(deleted.getId()));
    }

    @Test
    public void deleteAllForComponent() {
        List<Long> actualIngredientIds = getAllIdsByComponentId(componentId);
        assertThat(actualIngredientIds).isNotEmpty();
        ingredientService.deleteAllForComponent(componentId);
        assertThat(getAllIdsByComponentId(componentId)).isEmpty();
    }

    @Test
    public void deleteAllForRecipe() {
        List<Long> actualIngredientIds = componentService.getAllForRecipe(recipeId).stream()
                .flatMap(c -> c.getIngredients().stream())
                .map(Ingredient::getId)
                .collect(Collectors.toList());
        assertThat(actualIngredientIds).isNotEmpty();
        ingredientService.deleteAllForRecipe(recipeId);
        assertThat(componentService.getAllForRecipe(recipeId).stream()
                .flatMap(c -> c.getIngredients().stream())
                .map(Ingredient::getId)
                .collect(Collectors.toList())).isEmpty();
    }

    private List<Long> getAllIdsByComponentId(Long componentId) {
        return ingredientService.getByComponentId(componentId).stream()
                .map(Ingredient::getId)
                .sorted(Long::compareTo)
                .collect(Collectors.toList());
    }
}