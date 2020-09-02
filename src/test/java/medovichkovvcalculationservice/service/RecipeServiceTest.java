package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Recipe;
import medovichkovvcalculationservice.enums.PrivacyType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeServiceTest extends AbstractDBTest {

    @Autowired
    private RecipeService recipeService;

    @Test
    public void getByIdAndUser() {
        Recipe actual = recipeService.getByIdAndUser(recipeId, userId);
        Recipe expected = new Recipe(recipeId, userId, null, "Медовик", LocalDateTime.now(), true, PrivacyType.ALL);
        assertThat(actual)
                .isEqualToIgnoringGivenFields(expected, "components", "creationDate");
    }

    @Test
    public void getByIncorrectUserId() {
        Recipe actual = recipeService.getByIdAndUser(recipeId, 5L);
        assertThat(actual).isNull();
    }

    @Test
    public void getWithComponents() {
        Recipe actual = recipeService.getByIdAndUserWithComponents(recipeId, userId);
        assertThat(actual.getComponents().size() > 0);
    }

    @Test
    public void getAllForCurrentUser() {
        List<Long> actualIds = recipeService.getAllForCurrentUser(userId).stream()
                .map(Recipe::getId)
                .collect(Collectors.toList());
        assertThat(actualIds).isEqualTo(EXPECTED_IDS);
    }

    @Test
    public void getAll() {
        List<Long> actualIds = recipeService.getAll().stream()
                .map(Recipe::getId)
                .collect(Collectors.toList());
        assertThat(actualIds).isEqualTo(EXPECTED_IDS);
    }

    @Test
    public void saveNew() {
        Recipe actual = recipeService.save(new Recipe(null, 2L, null, "Медовик", LocalDateTime.now(), true, PrivacyType.ALL));
        Recipe expected = recipeService.getByIdAndUser(actual.getId(), 2L);
        assertThat(actual).
                isEqualToIgnoringGivenFields(expected, "creationDate", "components");
    }

    @Test
    public void updateExist() {
        String newName = "Медовичок";
        boolean isFavourite = false;
        Recipe expected = new Recipe(recipeId, userId, null, newName, LocalDateTime.now(), isFavourite, PrivacyType.ALL);
        Recipe actual = recipeService.getByIdAndUser(recipeId, userId);
        actual.setName(newName);
        actual.setFavorite(isFavourite);
        assertThat(recipeService.save(actual)).
                isEqualToIgnoringGivenFields(expected, "creationDate", "components");
    }

    @Test
    public void delete() {
        Recipe deleted = recipeService.getByIdAndUser(recipeId, userId);
        assertThat(deleted).isNotNull();
        assertThat(recipeService.delete(deleted.getId(), deleted.getUserId()));
    }

    @Test
    public void deleteNotUser() {
        assertThat(recipeService.delete(recipeId, 2L)).isFalse();
    }
}