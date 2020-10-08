package medovichkovvcalculationservice.dto;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static medovichkovvcalculationservice.calculation.CalculationUtils.getListSum;

/**
 * Util class for working with recipe, component and ingredient DTOs
 *
 * @author ivand on 09.10.2020
 */
public abstract class DtoUtils {

    public static RecipeDTO createFromRecipe(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        if (recipe == null) {
            return recipeDTO;
        }
        recipeDTO.setName(recipe.getName());
        recipeDTO.setSquare(recipe.getSquare());
        recipeDTO.setComponentDTOs(recipe.getComponents().stream()
                .map(component -> createFromComponent(component))
                .collect(Collectors.toList()));
        var componentDTOs = recipeDTO.getComponentDTOs();
        recipeDTO.setWeight(getListSum(componentDTOs.stream()
                .map(ComponentDTO::getWeight)
                .collect(toList())));
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
        componentDTO.setName(component.getName());
        componentDTO.setQuantity(component.getQuantity());
        componentDTO.setType(component.getType());

        List<Ingredient> ingredients = component.getIngredients();
        componentDTO.setIngredientDTOs(ingredients.stream()
                .map(ingredient -> createFromIngredient(ingredient))
                .collect(toList()));
        componentDTO.setWeight(getListSum(ingredients.stream()
                .map(Ingredient::getWeight)
                .collect(toList())));
        componentDTO.setCost(getListSum(ingredients.stream()
                .map(Ingredient::getCost)
                .collect(toList())));
        return componentDTO;
    }

    public static IngredientDTO createFromIngredient(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        if (ingredient == null) {
            return ingredientDTO;
        }
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setType(ingredient.getType());
        ingredientDTO.setCost(ingredient.getCost());
        ingredientDTO.setWeight(ingredient.getWeight());
        return ingredientDTO;
    }
}
