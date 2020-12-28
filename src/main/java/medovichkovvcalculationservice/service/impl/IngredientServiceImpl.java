package medovichkovvcalculationservice.service.impl;

import medovichkovvcalculationservice.dto.DtoUtils;
import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.repository.IngredientRepository;
import medovichkovvcalculationservice.service.IngredientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static medovichkovvcalculationservice.dto.DtoUtils.createFromIngredient;

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
    public Ingredient getByIdAndUser(Long ingredientId, Long userId) {
        return ingredientRepository.getByIdAndUser(ingredientId, userId);
    }

    @Override
    public IngredientDTO getDtoByIdAndUser(Long ingredientId, Long userId) {
        return DtoUtils.createFromIngredient(ingredientRepository.getByIdAndUser(ingredientId, userId));
    }

    @Override
    public List<IngredientDTO> getAllForUser(Long userId) {
        return ingredientRepository.getAllForUser(userId).stream()
                .map(DtoUtils::createFromIngredient)
                .collect(Collectors.toList());
    }

    @Override
    public IngredientDTO save(IngredientDTO ingredientDTO) {
        Ingredient ingredient = DtoUtils.createToIngredient(ingredientDTO);
        return createFromIngredient(save(ingredient));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(Long ingredientId) {
        ingredientRepository.delete(ingredientId);
    }

    @Override
    public void deleteIngredients(List<Long> ingredientIds) {
        ingredientRepository.deleteIngredients(ingredientIds);
    }
}
