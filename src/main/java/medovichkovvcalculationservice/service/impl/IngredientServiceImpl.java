package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.repository.IngredientRepository;
import medovichkovvcalculationservice.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ivand on 18.10.2020
 */
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getById(Long aLong) {
        throw new IllegalStateException("Get by id  method doesn`t supported yet");
    }

    @Override
    public boolean delete(Long ingredientId) {
        return ingredientRepository.delete(ingredientId);
    }

    @Override
    public boolean deleteIngredients(List<Long> ingredientIds) {
        return ingredientRepository.deleteIngredients(ingredientIds);
    }
}
