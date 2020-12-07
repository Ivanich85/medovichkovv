package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.entity.Ingredient;

import java.util.List;

public interface IngredientService extends CrudService<Ingredient, Long> {

    IngredientDTO getByIdAndUser(Long ingredientId, Long userId);

    List<IngredientDTO> getAllForUser(Long userId);

    IngredientDTO save(IngredientDTO ingredientDTO);

    boolean delete(Long ingredientId);

    boolean deleteIngredients(List<Long> ingredientIds);
}
