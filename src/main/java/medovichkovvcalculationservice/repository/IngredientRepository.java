package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.entity.Ingredient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ivand on 18.10.2020
 */
@Repository
@Transactional
public class IngredientRepository extends AbstractRepository{

    public Ingredient save(Ingredient ingredient) {
        if (ingredient.getId() == null) {
            entityManager.persist(ingredient);
            return ingredient;
        }
        return entityManager.merge(ingredient);
    }

    public boolean delete(Long ingredientId) {
        removeAllRelatedIngredients(List.of(ingredientId));
        return entityManager.createQuery(
                "delete " +
                        "from Ingredient i " +
                        "where i.id = :ingredientId")
                .setParameter("ingredientId", ingredientId)
                .executeUpdate() != 0;
    }

    public boolean deleteIngredients(List<Long> ingredientIds) {
        removeAllRelatedIngredients(ingredientIds);
        return entityManager.createQuery(
                "delete " +
                        "from Ingredient i " +
                        "where i.id in :ingredientIds")
                .setParameter("ingredientIds", ingredientIds)
                .executeUpdate() != 0;
    }

    private void removeAllRelatedIngredients(List<Long> ingredientIds) {
        entityManager.createQuery(
                "delete " +
                        "from RecipeIngredient ri " +
                        "where ri.ingredient.id in :ingredientIds")
                .setParameter("ingredientIds", ingredientIds)
                .executeUpdate();
    }
}
