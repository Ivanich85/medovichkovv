package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.dto.DtoUtils;
import medovichkovvcalculationservice.dto.RecipeIngredientDTO;
import medovichkovvcalculationservice.dto.RecipeDTO;
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
        return new Recipe(100L, 1L, createComponents(List.of(ONE, TWO, THREE)),
                "Медовик", BigDecimal.valueOf(254.30), 10,
                LocalDateTime.now(), true, PrivacyType.ALL);
    }

    public static List<Component> createComponents(List<EntityNumber> numbers) {
        if (CollectionUtils.isEmpty(numbers)) {
            return new ArrayList<>();
        }
        return numbers.stream()
                .map(TestCalculationDataUtils::createComponent)
                .collect(Collectors.toList());
    }

    public static Component createComponent(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Component(1000L, null, "Коржи",
                        ComponentType.CAKE, 12, createRecipeIngredients(List.of(ONE, TWO)));
            case TWO:
                return new Component(1001L, null, "Крем сметанный",
                        ComponentType.CREAM, 1, createRecipeIngredients(List.of(THREE, FOUR)));
            case THREE:
                return new Component(1002L, null, "Крем заварной",
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
                .map(TestCalculationDataUtils::createRecipeIngredient)
                .collect(Collectors.toList());
    }

    public static RecipeIngredient createRecipeIngredient(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new RecipeIngredient(100000L, createIngredient(ONE), null, BigDecimal.valueOf(3));
            case TWO:
                return new RecipeIngredient(100001L, createIngredient(TWO), null, BigDecimal.valueOf(500));
            case THREE:
                return new RecipeIngredient(100002L, createIngredient(THREE), null, BigDecimal.valueOf(350));
            case FOUR:
                return new RecipeIngredient(100003L, createIngredient(FOUR), null, BigDecimal.valueOf(100));
            case FIVE:
                return new RecipeIngredient(100004L, createIngredient(FIVE), null, BigDecimal.valueOf(500));
            case SIX:
                return new RecipeIngredient(100005L, createIngredient(SIX), null, BigDecimal.valueOf(5));
            default:
                return null;
        }
    }

    public static Ingredient createIngredient(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Ingredient(10000L,
                        "Яйца", BigDecimal.valueOf(10), IngredientQtyType.PIECE, null, BigDecimal.valueOf(75));
            case TWO:
                return new Ingredient(10001L,
                        "Сахар", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, null, BigDecimal.valueOf(35));
            case THREE:
                return new Ingredient(10006L,
                        "Сметана", BigDecimal.valueOf(350), IngredientQtyType.GRAM, null, BigDecimal.valueOf(80));
            case FOUR:
                return new Ingredient(10007L,
                        "Сахарная пудра", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, null, BigDecimal.valueOf(100));
            case FIVE:
                return new Ingredient(10010L,
                        "Молоко", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, null, BigDecimal.valueOf(50));
            case SIX:
                return new Ingredient(10011L,
                        "Желтки", BigDecimal.valueOf(10), IngredientQtyType.PIECE, null, BigDecimal.valueOf(75));
            default:
                return null;
        }
    }

    public static RecipeDTO createRecipeDTO() {
        RecipeDTO expectedRecipeDTO = new RecipeDTO();
        expectedRecipeDTO.setName("Медовик");
        expectedRecipeDTO.setSquare(BigDecimal.valueOf(254.30));
        expectedRecipeDTO.setCost(BigDecimal.valueOf(192.5));
        List<ComponentDTO> componentDTOS = createComponents(List.of(ONE, TWO, THREE)).stream()
                .map(DtoUtils::createFromComponent)
                .collect(Collectors.toList());
        expectedRecipeDTO.setComponentDTOs(componentDTOS);
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

    public static RecipeIngredientDTO createIngredientDTO() {
        RecipeIngredientDTO expectedRecipeIngredientDTO = new RecipeIngredientDTO();
        expectedRecipeIngredientDTO.setName("Яйца");
        expectedRecipeIngredientDTO.setType(IngredientQtyType.PIECE);
        expectedRecipeIngredientDTO.setWeight(BigDecimal.valueOf(3).setScale(2));
        expectedRecipeIngredientDTO.setCost(BigDecimal.valueOf(22.5));
        return expectedRecipeIngredientDTO;
    }
}
