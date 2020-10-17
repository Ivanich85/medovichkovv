package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.repository.ComponentRepository;
import medovichkovvcalculationservice.repository.RecipeIngredientRepository;
import medovichkovvcalculationservice.service.ComponentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ivand on 12.08.2020
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public ComponentServiceImpl(ComponentRepository componentRepository,
                                RecipeIngredientRepository recipeIngredientRepository) {
        this.componentRepository = componentRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
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
        return componentRepository.save(component);
    }

    @Override
    public boolean delete(Long componentId) {
        return recipeIngredientRepository.deleteAllForComponent(componentId)
                && componentRepository.delete(componentId);
    }

    @Override
    public boolean deleteAllForRecipe(Long recipeId) {
        return recipeIngredientRepository.deleteAllForRecipe(recipeId)
                && componentRepository.deleteAllForRecipe(recipeId);
    }
}
