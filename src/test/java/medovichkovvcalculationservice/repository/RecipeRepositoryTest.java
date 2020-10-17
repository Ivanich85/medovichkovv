package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.MedovichkovvCalculationService;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.enums.PrivacyType;
import medovichkovvcalculationservice.service.TestCalculationDataUtils;
import medovichkovvcalculationservice.testconfiguration.TestDataSourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test data source configuration only
 */
@SpringBootTest(classes = MedovichkovvCalculationService.class)
@ContextConfiguration(classes = TestDataSourceConfig.class)
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void saveRecipe() {
        // TODO - не сохраняются компоненты и ингредиенты. Исправить
        Long userId = 1000L;
        Recipe recipe = TestCalculationDataUtils.createRecipe();
        Recipe recipe1 = new Recipe(null, userId, null,
                "Тестовый медовик", BigDecimal.valueOf(254.30), 10,
                LocalDateTime.now(), true, PrivacyType.ALL);
        recipe = recipeRepository.save(recipe);
        Long recipeId = recipe.getId();
        assertNotNull(recipeId);
        //assertTrue(recipeRepository.delete(recipeId, userId));
        //assertNull(recipeRepository.getByIdAndUser(recipeId, userId));
    }
}