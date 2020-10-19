package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.repository.ComponentRepository;
import medovichkovvcalculationservice.service.ComponentService;
import medovichkovvcalculationservice.service.RecipeIngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ivand on 12.08.2020
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;
    private final RecipeIngredientService recipeIngredientService;

    public ComponentServiceImpl(ComponentRepository componentRepository,
                                RecipeIngredientService recipeIngredientService) {
        this.componentRepository = componentRepository;
        this.recipeIngredientService = recipeIngredientService;
    }

    @Override
    public Component getById(Long componentId) {
        return componentRepository.getById(componentId);
    }

    @Override
    public List<Component> getAllForRecipe(Long recipeId) {
        return componentRepository.getAllForRecipe(recipeId);
    }

    @Override
    public Component save(Component component) {
        componentRepository.save(component).getRecipeIngredients().stream()
                .filter(recipeIngredient -> recipeIngredient.getComponent() == null)
                .forEach(recipeIngredient -> {
                    recipeIngredient.setComponent(component);
                    recipeIngredientService.save(recipeIngredient);
                });
        return component;
    }

    @Override
    public boolean delete(Long componentId) {
        return recipeIngredientService.deleteAllForComponent(componentId)
                && componentRepository.delete(componentId);
    }

    @Override
    public boolean deleteAllForRecipe(Long recipeId) {
        return recipeIngredientService.deleteAllForRecipe(recipeId)
                && componentRepository.deleteAllForRecipe(recipeId);
    }
}
