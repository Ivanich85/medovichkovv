package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.repository.ComponentRepository;
import medovichkovvcalculationservice.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ivand on 12.08.2020
 */
@Service
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;
    private final IngredientRepository ingredientRepository;

    public ComponentServiceImpl(ComponentRepository componentRepository,
                                IngredientRepository ingredientRepository) {
        this.componentRepository = componentRepository;
        this.ingredientRepository = ingredientRepository;
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
        return ingredientRepository.deleteAllForComponent(componentId)
                && componentRepository.delete(componentId);
    }

    @Override
    public boolean deleteAllForRecipe(Long recipeId) {
        return ingredientRepository.deleteAllForRecipe(recipeId)
                && componentRepository.deleteAllForRecipe(recipeId);
    }
}
