package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Ingredient;
import medovichkovvcalculationservice.enums.IngredientQtyType;
import medovichkovvcalculationservice.repository.IngredientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class IngredientServiceTest extends AbstractDBTest {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private IngredientRepository  ingredientRepository;

    @Test
    public void getById() {
        Ingredient expected = new Ingredient(ingredientId, null, "Яйца", 10.0, IngredientQtyType.PIECE, BigDecimal.valueOf(75).setScale(2));
        Ingredient actual = ingredientService.getById(ingredientId);
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
        Ingredient expected = ingredientService.save(new Ingredient(ingredientId, componentService.getById(componentId), "Яйца", 10.0, IngredientQtyType.PIECE, BigDecimal.valueOf(75).setScale(2)));
        Ingredient actual = ingredientService.getById(expected.getId());
        assertThat(actual).
                isEqualToIgnoringGivenFields(expected, "component");
    }

    @Test
    public void updateExist() {
        String newName = "Шоколад";
        IngredientQtyType type = IngredientQtyType.GRAM;
        Ingredient expectedIngredient = new Ingredient(ingredientId, componentService.getById(componentId), newName, 10.0, type, BigDecimal.valueOf(75).setScale(2));
        Ingredient actualIngredient = ingredientService.getById(ingredientId);
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