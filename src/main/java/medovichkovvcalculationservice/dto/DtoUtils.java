package medovichkovvcalculationservice.dto;

import medovichkovvcalculationservice.calculation.CalculationUtils;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static medovichkovvcalculationservice.calculation.CalculationUtils.getListSum;

/**
 * Util class for working with recipe, component and ingredient DTOs
 *
 * @author ivand on 09.10.2020
 *
 */
public abstract class DtoUtils {

    public static RecipeDTO createFromRecipe(Recipe recipe) throws IllegalStateException {
        RecipeDTO recipeDTO = new RecipeDTO();
        if (recipe == null) {
            throw new IllegalStateException("Recipe can`t be null");
        }
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setSquare(recipe.getSquare());
        recipeDTO.setFavorite(recipe.isFavorite());
        recipeDTO.setCakes(recipe.getCakes());
        return recipeDTO;
    }

    public static RecipeDTO createFromRecipeWithSumAndComponents(Recipe recipe) {
        RecipeDTO recipeDTO = createFromRecipe(recipe);
        recipeDTO.setComponentDTOs(recipe.getComponents().stream()
                .map(DtoUtils::createFromComponent)
                .collect(toList()));
        var componentDTOs = recipeDTO.getComponentDTOs();
        recipeDTO.setCost(getListSum(componentDTOs.stream()
                .map(ComponentDTO::getCost)
                .collect(toList())));
        return recipeDTO;
    }

    public static ComponentDTO createFromComponent(Component component) {
        ComponentDTO componentDTO = new ComponentDTO();
        if (component == null) {
            return componentDTO;
        }
        componentDTO.setId(component.getId());
        componentDTO.setName(component.getName());
        componentDTO.setQuantity(component.getQuantity());
        componentDTO.setType(component.getType());
        componentDTO.setRecipeDtoId(component.getRecipe().getId());

        List<RecipeIngredient> ingredients = component.getRecipeIngredients();
        componentDTO.setRecipeIngredientDTOS(ingredients.stream()
                .map(DtoUtils::createFromRecipeIngredient)
                .collect(toList()));
        componentDTO.setCost(getListSum(componentDTO.getRecipeIngredientDTOS().stream()
                .map(ingrDto -> ingrDto.getIngredientDTO().getCost())
                .collect(toList())));
        return componentDTO;
    }

    public static RecipeIngredientDTO createFromRecipeIngredient(RecipeIngredient recipeIngredient) {
        RecipeIngredientDTO recipeIngredientDTO = new RecipeIngredientDTO();
        if (recipeIngredient == null) {
            return recipeIngredientDTO;
        }
        Ingredient ingredient = recipeIngredient.getIngredient();
        BigDecimal coef = CalculationUtils.divide(recipeIngredient.getQuantity(), ingredient.getWeight());

        IngredientDTO ingredientDTO = createFromIngredient(ingredient);
        ingredientDTO.setCost(calcWithCoef(ingredient.getCost(), coef));

        recipeIngredientDTO.setId(recipeIngredient.getId());
        recipeIngredientDTO.setComponentId(recipeIngredient.getComponent().getId());
        recipeIngredientDTO.setIngredientDTO(ingredientDTO);
        recipeIngredientDTO.setQuantity(calcWithCoef(ingredient.getWeight(), coef));
        return recipeIngredientDTO;
    }

    public static IngredientDTO createFromIngredient(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        if (ingredient == null) {
            throw new IllegalStateException("Ingredient can`t be null");
        }
        ingredientDTO.setCost(ingredient.getCost());
        ingredientDTO.setWeight(ingredient.getWeight());
        ingredientDTO.setType(ingredient.getType());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setUserId(ingredient.getUserId());
        ingredientDTO.setId(ingredient.getId());
        return ingredientDTO;
    }

    public static Ingredient createToIngredient(IngredientDTO ingredientDTO) {
        if (ingredientDTO == null) {
            throw new IllegalStateException("Ingredient DTO can`t be null");
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setCost(ingredientDTO.getCost());
        ingredient.setWeight(ingredientDTO.getWeight());
        ingredient.setType(ingredientDTO.getType());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setUserId(ingredientDTO.getUserId());
        return ingredient;
    }

    private static BigDecimal calcWithCoef(BigDecimal value, BigDecimal coef) {
        return CalculationUtils.multiply(value, coef);
    }
}
