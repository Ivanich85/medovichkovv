package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Component;
import medovichkovvcalculationservice.enums.ComponentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ComponentServiceTest extends AbstractDBTest {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private RecipeService recipeService;

    @Test
    public void getById() {
        Component expectedComponent = new Component(componentId, null, "Коржи", ComponentType.CAKE, 12, null);
        Component actual = componentService.getById(componentId);
        assertThat(actual)
                .isEqualToIgnoringGivenFields(expectedComponent, "recipe", "ingredients");
    }

    @Test
    public void getAllForRecipe() {
        List<Long> expectedComponentIds = List.of(componentId, 1001L, 1002L, 1003L, 1004L, 1005L);
        List<Long> actualComponentIds = getComponentIdsByRecipeId(recipeId);
        assertThat(actualComponentIds).isEqualTo(expectedComponentIds);
    }

    @Test
    public void saveNew() {
        Component expected = componentService.save(new Component(1100L, recipeService.getByIdAndUser(recipeId, userId),
                "Коржи", ComponentType.CAKE, 12, null));
        Component actual = componentService.getById(expected.getId());
        assertThat(actual).
                isEqualToIgnoringGivenFields(expected, "recipe", "ingredients");
    }

    @Test
    public void updateExist() {
        String newName = "Квадратный корж";
        Integer quantity = 5;
        Component expectedComponent = new Component(componentId, recipeService.getByIdAndUser(recipeId, userId), newName, ComponentType.CAKE, quantity, null);
        Component actualComponent = componentService.getById(componentId);
        actualComponent.setName(newName);
        actualComponent.setQuantity(quantity);
        assertThat(componentService.save(actualComponent)).
                isEqualToIgnoringGivenFields(expectedComponent, "recipe", "ingredients");
    }

    @Test
    public void delete() {
        Component deleted = componentService.getById(componentId);
        assertThat(deleted).isNotNull();
        assertThat(componentService.delete(deleted.getId()));
    }

    @Test
    public void deleteAllForRecipe() {
        List<Long> expectedComponentIds = getComponentIdsByRecipeId(recipeId);
        assertThat(expectedComponentIds).isNotEmpty();
        componentService.deleteAllForRecipe(recipeId);
        assertThat(getComponentIdsByRecipeId(recipeId)).isEmpty();
    }

    private List<Long> getComponentIdsByRecipeId(Long recipeId) {
        return componentService.getAllForRecipe(recipeId).stream()
                .map(Component::getId)
                .sorted(Long::compareTo)
                .collect(Collectors.toList());
    }
}