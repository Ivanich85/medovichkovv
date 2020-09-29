package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.repository.ComponentRepository;
import medovichkovvcalculationservice.repository.IngredientRepository;
import medovichkovvcalculationservice.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ivand on 12.07.2020
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ComponentRepository componentRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             ComponentRepository componentRepository,
                             IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.componentRepository = componentRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Recipe getByIdAndUser(Long recipeId, Long userId) {
        return recipeRepository.getByIdAndUser(recipeId, userId);
    }

    public Recipe getByIdAndUserWithComponents(Long recipeId, Long userId) {
        return recipeRepository.getByIdAndUserWithComponents(recipeId, userId);
    }

    @Override
    public List<Recipe> getAllForCurrentUser(Long userId) {
        return recipeRepository.getAllForCurrentUser(userId);
    }

    @Override
    public List<Recipe> getAll() {
        return recipeRepository.getAll();
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public boolean delete(Long recipeId, Long userId) {
        return ingredientRepository.deleteAllForComponent(recipeId)
                && componentRepository.deleteAllForRecipe(recipeId)
                && recipeRepository.delete(recipeId, userId);
    }
}
