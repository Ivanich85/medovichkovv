package medovichkovvcalculationservice;

import medovichkovvcalculationservice.dto.*;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.enums.ComponentType;
import medovichkovvcalculationservice.enums.IngredientQtyType;
import medovichkovvcalculationservice.enums.PrivacyType;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.TestDataUtils.EntityNumber.*;

/**
 * @author ivand on 13.10.2020
 */
public abstract class TestDataUtils {

    private static final String TEST_RECIPE_NAME = "Тестовый медовик";
    public static final Long USER_ID = 1L;

    public enum EntityNumber {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX
    }

    public static Recipe createRecipe() {
        return new Recipe(null, 1L, createComponents(List.of(ONE, TWO, THREE)),
                TEST_RECIPE_NAME, BigDecimal.valueOf(254.30), 10,
                LocalDateTime.now(), true, PrivacyType.ALL);
    }

    public static List<Component> createComponents(List<EntityNumber> numbers) {
        if (CollectionUtils.isEmpty(numbers)) {
            return new ArrayList<>();
        }
        return numbers.stream()
                .map(TestDataUtils::createComponent)
                .collect(Collectors.toList());
    }

    public static Component createComponent(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Component(null, null, "Коржи",
                        ComponentType.CAKE, 12, createRecipeIngredients(List.of(ONE, TWO)));
            case TWO:
                return new Component(null, null, "Крем сметанный",
                        ComponentType.CREAM, 1, createRecipeIngredients(List.of(THREE, FOUR)));
            case THREE:
                return new Component(null, null, "Крем заварной",
                        ComponentType.CUSTARD, 1, createRecipeIngredients(List.of(FIVE, SIX)));
            default:
                return null;
        }
    }

    public static List<RecipeIngredient> createRecipeIngredients(List<EntityNumber> numbers) {
        if (CollectionUtils.isEmpty(numbers)) {
            return new ArrayList<>();
        }
        return numbers.stream()
                .map(TestDataUtils::createRecipeIngredient)
                .collect(Collectors.toList());
    }

    public static RecipeIngredient createRecipeIngredient(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new RecipeIngredient(null, createIngredient(ONE), null, BigDecimal.valueOf(3));
            case TWO:
                return new RecipeIngredient(null, createIngredient(TWO), null, BigDecimal.valueOf(500));
            case THREE:
                return new RecipeIngredient(null, createIngredient(THREE), null, BigDecimal.valueOf(350));
            case FOUR:
                return new RecipeIngredient(null, createIngredient(FOUR), null, BigDecimal.valueOf(100));
            case FIVE:
                return new RecipeIngredient(null, createIngredient(FIVE), null, BigDecimal.valueOf(500));
            case SIX:
                return new RecipeIngredient(null, createIngredient(SIX), null, BigDecimal.valueOf(5));
            default:
                return null;
        }
    }

    public static List<Ingredient> createAllIngredients() {
        return createIngredients(List.of(ONE, TWO, THREE, FOUR, FIVE, SIX));
    }

    public static List<Ingredient> createIngredients(List<EntityNumber> numbers) {
        if (CollectionUtils.isEmpty(numbers)) {
            return new ArrayList<>();
        }
        return numbers.stream()
                .map(TestDataUtils::createIngredient)
                .collect(Collectors.toList());
    }

    public static Ingredient createIngredient(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Ingredient(null, USER_ID,
                        "Яйца", BigDecimal.valueOf(10), IngredientQtyType.PIECE, null, BigDecimal.valueOf(75));
            case TWO:
                return new Ingredient(null, USER_ID,
                        "Сахар",  BigDecimal.valueOf(1000), IngredientQtyType.GRAM, null, BigDecimal.valueOf(35));
            case THREE:
                return new Ingredient(null, USER_ID,
                        "Сметана", BigDecimal.valueOf(350), IngredientQtyType.GRAM, null, BigDecimal.valueOf(80));
            case FOUR:
                return new Ingredient(null, USER_ID,
                        "Сахарная пудра", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, null, BigDecimal.valueOf(100));
            case FIVE:
                return new Ingredient(null, USER_ID,
                        "Молоко", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, null, BigDecimal.valueOf(50));
            case SIX:
                return new Ingredient(null, USER_ID,
                        "Желтки", BigDecimal.valueOf(10), IngredientQtyType.PIECE, null, BigDecimal.valueOf(75));
            default:
                return null;
        }
    }

    public static RecipeDTO createRecipeDTO() {
        RecipeDTO expectedRecipeDTO = new RecipeDTO();
        expectedRecipeDTO.setName(TEST_RECIPE_NAME);
        expectedRecipeDTO.setSquare(BigDecimal.valueOf(254.30));
        expectedRecipeDTO.setCost(BigDecimal.valueOf(192.5));
        List<ComponentDTO> componentDTOS = createComponents(List.of(ONE, TWO, THREE)).stream()
                .map(DtoUtils::createFromComponent)
                .collect(Collectors.toList());
        expectedRecipeDTO.setComponentDTOs(componentDTOS);
        expectedRecipeDTO.setFavorite(true);
        expectedRecipeDTO.setPrivacyType(PrivacyType.ALL);
        return expectedRecipeDTO;
    }

    public static ComponentDTO createComponentDTO() {
        ComponentDTO expectedConponentDTO = new ComponentDTO();
        expectedConponentDTO.setName("Коржи");
        expectedConponentDTO.setType(ComponentType.CAKE);
        expectedConponentDTO.setQuantity(12);
        expectedConponentDTO.setCost(BigDecimal.valueOf(40.0));
        return expectedConponentDTO;
    }

    public static RecipeIngredientDTO createRecipeIngredientDTO() {
        RecipeIngredientDTO expectedRecipeIngredientDTO = new RecipeIngredientDTO();
        expectedRecipeIngredientDTO.setName("Яйца");
        expectedRecipeIngredientDTO.setType(IngredientQtyType.PIECE);
        expectedRecipeIngredientDTO.setWeight(BigDecimal.valueOf(3).setScale(2));
        expectedRecipeIngredientDTO.setCost(BigDecimal.valueOf(22.5));
        return expectedRecipeIngredientDTO;
    }

    public static Ingredient createIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(10000L);
        ingredient.setCost(BigDecimal.valueOf(123.4));
        ingredient.setName("Мука");
        ingredient.setType(IngredientQtyType.GRAM);
        ingredient.setUserId(USER_ID);
        ingredient.setWeight(BigDecimal.valueOf(200.0));
        return ingredient;
    }

    public static IngredientDTO createIngredientDTO() {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(10000L);
        ingredientDTO.setCost(BigDecimal.valueOf(123.4));
        ingredientDTO.setName("Мука");
        ingredientDTO.setType(IngredientQtyType.GRAM);
        ingredientDTO.setUserId(USER_ID);
        ingredientDTO.setWeight(BigDecimal.valueOf(200.0));
        return ingredientDTO;
    }
}
