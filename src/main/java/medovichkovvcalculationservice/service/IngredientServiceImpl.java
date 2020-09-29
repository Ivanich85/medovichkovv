package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.repository.ComponentRepository;
import medovichkovvcalculationservice.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivand on 23.07.2020
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ComponentRepository componentRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 ComponentRepository componentRepository) {
        this.ingredientRepository = ingredientRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    public Ingredient getById(Long ingredientId) {
        return ingredientRepository.getById(ingredientId);
    }

    @Override
    public List<Ingredient> getByComponentId(Long componentId) {
        return ingredientRepository.getByComponentId(componentId);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public boolean delete(Long ingredientId) {
        return ingredientRepository.delete(ingredientId);
    }

    @Override
    public boolean deleteAllForComponent(Long componentId) {
        return ingredientRepository.deleteAllForComponent(componentId);
    }

    @Override
    public boolean deleteAllForRecipe(Long recipeId) {
        List<Long> componentIds = componentRepository.getAllForRecipe(recipeId).stream()
                .map(Component::getId)
                .collect(Collectors.toList());
        return ingredientRepository.deleteAllForComponents(componentIds);
    }
}
