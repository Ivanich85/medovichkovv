package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.dto.DtoUtils;
import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
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
                        ComponentType.CAKE, 12, createIngredients(List.of(ONE, TWO)));
            case TWO:
                return new Component(1001L, null, "Крем сметанный",
                        ComponentType.CREAM, 1, createIngredients(List.of(THREE, FOUR)));
            case THREE:
                return new Component(1002L, null, "Крем заварной",
                        ComponentType.CUSTARD, 1, createIngredients(List.of(FIVE, SIX)));
            default:
                return null;
        }
    }

    public static List<Ingredient> createIngredients(List<EntityNumber> numbers) {
        if (CollectionUtils.isEmpty(numbers)) {
            return new ArrayList<>();
        }
        return numbers.stream()
                .map(TestCalculationDataUtils::createIngredient)
                .collect(Collectors.toList());
    }

    public static Ingredient createIngredient(EntityNumber entityNumber) {
        switch (entityNumber) {
            case ONE:
                return new Ingredient(10000L, null,
                        "Яйца", BigDecimal.valueOf(10), IngredientQtyType.PIECE, BigDecimal.valueOf(75));
            case TWO:
                return new Ingredient(10001L, null,
                        "Сахар", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, BigDecimal.valueOf(35));
            case THREE:
                return new Ingredient(10006L, null,
                        "Сметана", BigDecimal.valueOf(350), IngredientQtyType.GRAM, BigDecimal.valueOf(80));
            case FOUR:
                return new Ingredient(10007L, null,
                        "Сахарная пудра", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, BigDecimal.valueOf(100));
            case FIVE:
                return new Ingredient(10010L, null,
                        "Молоко", BigDecimal.valueOf(1000), IngredientQtyType.GRAM, BigDecimal.valueOf(50));
            case SIX:
                return new Ingredient(10011L, null,
                        "Желтки", BigDecimal.valueOf(10), IngredientQtyType.PIECE, BigDecimal.valueOf(75));
            default:
                return null;
        }
    }

    public static RecipeDTO createRecipeDTO() {
        RecipeDTO expectedRecipeDTO = new RecipeDTO();
        expectedRecipeDTO.setName("Медовик");
        expectedRecipeDTO.setSquare(BigDecimal.valueOf(254.30));
        expectedRecipeDTO.setCost(BigDecimal.valueOf(415));
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
        expectedConponentDTO.setCost(BigDecimal.valueOf(110));
        return expectedConponentDTO;
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
