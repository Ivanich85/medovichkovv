package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.Recipe;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for calculation new yummy
 *
 * @author ivand on 06.09.2020
 */
@Service
public class CalculationServiceImpl implements CalculationService {

    private final RecipeService recipeService;

    public CalculationServiceImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public RecipeDTO calculateRecipe(Long baseRecipeId, Long userId, BigDecimal expectedSquare) {

        // TODO Получаем рецепт
        Recipe baseRecipe = recipeService.getByIdAndUser(baseRecipeId, userId);

        BigDecimal baseRecipeSquare = baseRecipe.getSquare();
        BigDecimal calculationCoef = expectedSquare.divide(baseRecipeSquare);

        List<Component> components = baseRecipe.getComponents();
        List<Component> recalculationComponents = new ArrayList<>();
        for (Component component : components) {
            Component recalcComponent = new Component();
            recalcComponent.setQuantity(component.getQuantity());
            recalcComponent.setName(component.getName());
            recalcComponent.setRecipe(component.getRecipe());
            recalcComponent.setType(component.getType());

            List<Ingredient> recalculationIngredients = new ArrayList<>();
            for (Ingredient ingredient : component.getIngredients()) {
                Ingredient recalcIngredient = new Ingredient();
                recalcIngredient.setName(ingredient.getName());
                recalcIngredient.setType(ingredient.getType());
                recalcIngredient.setComponent(ingredient.getComponent());
                recalcIngredient.setCost(ingredient.getCost().multiply(calculationCoef, new MathContext(2, RoundingMode.HALF_UP)));
                recalcIngredient.setWeight(ingredient.getWeight().multiply(calculationCoef, new MathContext(3, RoundingMode.HALF_UP)));
                recalculationIngredients.add(recalcIngredient);
            }

            recalcComponent.setIngredients(recalculationIngredients);
        }
        RecipeDTO recalcRecipe = RecipeDTO.createFromRecipeNoComponents(baseRecipe);

        return recalcRecipe;
    }
}
