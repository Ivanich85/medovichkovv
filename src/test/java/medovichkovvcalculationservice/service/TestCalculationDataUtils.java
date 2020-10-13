package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.enums.ComponentType;
import medovichkovvcalculationservice.enums.IngredientQtyType;
import medovichkovvcalculationservice.enums.PrivacyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.EntityNumber.*;

/**
 * @author ivand on 13.10.2020
 */
public abstract class TestCalculationDataUtils {

    public enum EntityNumber {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX
    }

    public static Recipe createRecipe() {
        return new Recipe(100L, 1L, new ArrayList<>(),
                "Медовик", BigDecimal.valueOf(254.30), 10,
                LocalDateTime.now(), true, PrivacyType.ALL);
    }

    public static Component createComponent(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Component(1000L, createRecipe(), "Коржи",
                        ComponentType.CAKE, 12, new ArrayList<>());
            case TWO:
                return new Component(1001L, createRecipe(), "Крем сметанный",
                        ComponentType.CREAM, 1, new ArrayList<>());
            case THREE:
                return new Component(1002L, createRecipe(), "Крем заварной",
                        ComponentType.CUSTARD, 1, new ArrayList<>());
            default:
                return null;
        }
    }

    public static Ingredient createIngredient(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Ingredient(10000L, createComponent(ONE),
                        "Яйца", BigDecimal.valueOf(10), IngredientQtyType.PIECE, BigDecimal.valueOf(75));
            case TWO:
                return new Ingredient(10001L, createComponent(ONE),
                        "Сахар", BigDecimal.valueOf(10), IngredientQtyType.GRAM, BigDecimal.valueOf(35));
            case THREE:
                return new Ingredient(10006L, createComponent(TWO),
                        "Сметана", BigDecimal.valueOf(350), IngredientQtyType.GRAM, BigDecimal.valueOf(80));
            case FOUR:
                return new Ingredient(10007L, createComponent(TWO),
                        "Сахарная пудра", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, BigDecimal.valueOf(100));
            case FIVE:
                return new Ingredient(10010L, createComponent(THREE),
                        "Молоко", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, BigDecimal.valueOf(50));
            case SIX:
                return new Ingredient(10011L, createComponent(THREE),
                        "Желтки", BigDecimal.valueOf(10), IngredientQtyType.PIECE, BigDecimal.valueOf(75));
            default:
                return null;
        }
    }

    public static RecipeDTO createRecipeDTO() {
        RecipeDTO expectedRecipeDTO = new RecipeDTO();
        expectedRecipeDTO.setName("Медовик");
        expectedRecipeDTO.setComponentDTOs(new ArrayList<>());
        expectedRecipeDTO.setSquare(BigDecimal.valueOf(254.30));
        expectedRecipeDTO.setCost(BigDecimal.valueOf(415));
        return expectedRecipeDTO;
    }

    public static IngredientDTO createIngredientDTO() {
        IngredientDTO expectedIngredientDTO = new IngredientDTO();
        expectedIngredientDTO.setName("Яйца");
        expectedIngredientDTO.setType(IngredientQtyType.PIECE);
        expectedIngredientDTO.setWeight(BigDecimal.valueOf(10));
        expectedIngredientDTO.setCost(BigDecimal.valueOf(75));
        return expectedIngredientDTO;
    }
}
