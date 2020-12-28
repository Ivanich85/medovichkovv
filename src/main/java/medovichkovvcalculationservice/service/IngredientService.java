package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.entity.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient getByIdAndUser(Long ingredientId, Long userId);

    IngredientDTO getDtoByIdAndUser(Long ingredientId, Long userId);

    List<IngredientDTO> getAllForUser(Long userId);

    IngredientDTO save(IngredientDTO ingredientDTO);

    Ingredient save(Ingredient ingredient);

    void delete(Long ingredientId);

    void deleteIngredients(List<Long> ingredientIds);
}
