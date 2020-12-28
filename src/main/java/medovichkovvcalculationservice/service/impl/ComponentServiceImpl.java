package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.dto.DtoUtils;
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
    public ComponentDTO getDtoById(Long componentId) {
        return DtoUtils.createFromComponent(componentRepository.getById(componentId));
    }

    @Override
    public List<Component> getAllForRecipe(Long recipeId) {
        return componentRepository.getAllForRecipe(recipeId);
    }

    @Override
    public Component save(Component component) {
        componentRepository.save(component).getRecipeIngredients().forEach(recipeIngredient -> {
                    recipeIngredient.setComponent(component);
                    recipeIngredientService.save(recipeIngredient);
                });
        return component;
    }

    @Override
    public void delete(Long componentId) {
        recipeIngredientService.deleteAllForComponent(componentId);
        componentRepository.delete(componentId);
    }

    @Override
    public void deleteAllForRecipe(Long recipeId) {
        recipeIngredientService.deleteAllForRecipe(recipeId);
        componentRepository.deleteAllForRecipe(recipeId);
    }
}
