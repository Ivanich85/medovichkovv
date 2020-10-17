package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.repository.ComponentRepository;
import medovichkovvcalculationservice.repository.RecipeIngredientRepository;
import medovichkovvcalculationservice.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivand on 23.07.2020
 */
@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService {

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final ComponentRepository componentRepository;

    public RecipeIngredientServiceImpl(RecipeIngredientRepository recipeIngredientRepository,
                                       ComponentRepository componentRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    public RecipeIngredient getById(Long recipeIngredientId) {
        return recipeIngredientRepository.getById(recipeIngredientId);
    }

    @Override
    public List<RecipeIngredient> getByComponentId(Long componentId) {
        return recipeIngredientRepository.getByComponentId(componentId);
    }

    @Override
    public RecipeIngredient save(RecipeIngredient ingredient) {
        return recipeIngredientRepository.save(ingredient);
    }

    @Override
    public boolean delete(Long ingredientId) {
        return recipeIngredientRepository.delete(ingredientId);
    }

    @Override
    public boolean deleteAllForComponent(Long componentId) {
        return recipeIngredientRepository.deleteAllForComponent(componentId);
    }

    @Override
    public boolean deleteAllForRecipe(Long recipeId) {
        List<Long> componentIds = componentRepository.getAllForRecipe(recipeId).stream()
                .map(Component::getId)
                .collect(Collectors.toList());
        return recipeIngredientRepository.deleteAllForComponents(componentIds);
    }
}
