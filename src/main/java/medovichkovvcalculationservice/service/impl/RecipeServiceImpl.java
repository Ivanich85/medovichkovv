package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.repository.RecipeRepository;
import medovichkovvcalculationservice.service.ComponentService;
import medovichkovvcalculationservice.service.RecipeIngredientService;
import medovichkovvcalculationservice.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ivand on 12.07.2020
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ComponentService componentService;
    private final RecipeIngredientService recipeIngredientService;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             ComponentService componentService,
                             RecipeIngredientService recipeIngredientService) {
        this.recipeRepository = recipeRepository;
        this.componentService = componentService;
        this.recipeIngredientService = recipeIngredientService;
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
    public List<Recipe> getAll() {
        return recipeRepository.getAll();
    }

    @Override
    public Recipe save(Recipe recipe) {
        recipeRepository.save(recipe).getComponents().stream()
                .filter(component -> component.getRecipe() == null)
                .forEach(component ->  {
                    component.setRecipe(recipe);
                    componentService.save(component);
                });
        return recipe;
    }

    @Override
    public boolean delete(Long recipeId, Long userId) {
        var compDelete = componentService.deleteAllForRecipe(recipeId);
        var recipeDelete = recipeRepository.delete(recipeId, userId);
        return compDelete && recipeDelete;
    }
}
