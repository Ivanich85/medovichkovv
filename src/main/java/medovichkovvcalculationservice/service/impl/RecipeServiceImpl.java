package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.calculation.CalcServiceFactory;
import medovichkovvcalculationservice.calculation.CalculationUtils;
import medovichkovvcalculationservice.calculation.ServiceType;
import medovichkovvcalculationservice.dto.DtoUtils;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.exception.CalculationException;
import medovichkovvcalculationservice.repository.RecipeRepository;
import medovichkovvcalculationservice.service.ComponentService;
import medovichkovvcalculationservice.service.RecipeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;
import static medovichkovvcalculationservice.dto.DtoUtils.createFromRecipeWithSumAndComponents;

/**
 * @author ivand on 12.07.2020
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ComponentService componentService;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             ComponentService componentService) {
        this.recipeRepository = recipeRepository;
        this.componentService = componentService;
    }

    @Override
    public Recipe getByIdAndUser(Long recipeId, Long userId) {
        return recipeRepository.getByIdAndUser(recipeId, userId);
    }

    @Override
    public Recipe getByIdAndUserWithComponents(Long recipeId, Long userId) {
        return recipeRepository.getByIdAndUserWithComponents(recipeId, userId);
    }

    @Override
    public List<Recipe> getAllForCurrentUser(Long userId) {
        return recipeRepository.getAllForCurrentUser(userId);
    }

    @Override
    public Recipe save(Recipe recipe) {
        recipeRepository.save(recipe).getComponents().forEach(component ->  {
                    component.setRecipe(recipe);
                    componentService.save(component);
                });
        return recipe;
    }

    @Override
    public void delete(Long recipeId, Long userId) {
        componentService.deleteAllForRecipe(recipeId);
        recipeRepository.delete(recipeId, userId);
    }

    @Override
    public RecipeDTO recalculateRecipe(Long baseRecipeId, Long userId, BigDecimal newSquare, Integer cakes) {
        ServiceType serviceType = checkServiceType(newSquare, cakes);
        Recipe baseRecipe = getByIdAndUserWithComponents(baseRecipeId, userId);
        if (baseRecipe == null) {
            throw new IllegalStateException(String.format("Recipe id %s for user %s not found", baseRecipeId, userId));
        }
        var recalcCoef = getRecalculationCoef(baseRecipe, newSquare, cakes);
        return CalcServiceFactory.getCalcService(serviceType).calculate(baseRecipe, recalcCoef);
    }

    @Override
    public List<RecipeDTO> getAllRecipesForUser(Long userId) {
        return getAllForCurrentUser(userId).stream()
                .map(DtoUtils::createFromRecipe)
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDTO getRecipeForUser(Long recipeId, Long userId) {
        return createFromRecipeWithSumAndComponents(getByIdAndUserWithComponents(recipeId, userId));
    }

    private ServiceType checkServiceType(BigDecimal newSquare, Integer cakes) {
        if (newSquare == null && cakes == null) {
            throw new CalculationException("New recipe square and cakes can`t be null");
        }
        if (newSquare != null && cakes != null) {
            throw new CalculationException("New recipe square and cakes not null. It`s ambiguity");
        }
        return newSquare != null ? ServiceType.SQUARE : ServiceType.CAKE;
    }

    private BigDecimal getRecalculationCoef(Recipe recalcRecipe, BigDecimal newSquare, Integer cakes) {
        return newSquare != null ?
                CalculationUtils.divide(newSquare, recalcRecipe.getSquare()) :
                CalculationUtils.divide(valueOf(cakes), valueOf(recalcRecipe.getCakes()));
    }
}
