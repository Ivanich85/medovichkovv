package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.Ingredient;

import java.util.List;

public interface IngredientService extends CrudService<Ingredient, Long> {

    Ingredient save(Ingredient ingredient);

    boolean delete(Long ingredientId);

    boolean deleteIngredients(List<Long> ingredientIds);
}
