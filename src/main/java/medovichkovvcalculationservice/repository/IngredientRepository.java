package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.entity.Ingredient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivand on 23.07.2020
 */
@Repository
@Transactional
public class IngredientRepository extends AbstractRepository {

    @Transactional(readOnly = true)
    public Ingredient getById(Long ingredientId) {
        return entityManager.find(Ingredient.class, ingredientId);
    }

    @Transactional(readOnly = true)
    public List<Ingredient> getByComponentId(Long componentId) {
        return entityManager.createQuery(
                "select i from Ingredient i " +
                        "where i.component.id = :componentId", Ingredient.class)
                .setParameter("componentId", componentId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Ingredient> getByRecipeId(Long recipeId) {
        return entityManager.createQuery(
                "select i from Ingredient i " +
                        "where i.component.recipe.id = :recipeId", Ingredient.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    public Ingredient save(Ingredient ingredient) {
        if (ingredient.getId() == null) {
            entityManager.persist(ingredient);
            return ingredient;
        }
        return entityManager.merge(ingredient);
    }

    public boolean delete(Long ingredientId) {
        return entityManager.createQuery(
                "delete " +
                        "from Ingredient i " +
                        "where i.id = :ingredientId")
                .setParameter("ingredientId", ingredientId)
                .executeUpdate() != 0;
    }

    public boolean deleteAllForComponent(Long componentId) {
        return entityManager.createQuery(
                "delete " +
                        "from Ingredient i " +
                        "where i.component.id = :componentId")
                .setParameter("componentId", componentId)
                .executeUpdate() != 0;
    }

    public boolean deleteAllForComponents(List<Long> componentIds) {
        return entityManager.createQuery(
                "delete " +
                        "from Ingredient i " +
                        "where i.component.id in :componentIds")
                .setParameter("componentIds", componentIds)
                .executeUpdate() != 0;
    }

    public boolean deleteAllForRecipe(Long recipeId) {
        List<Long> recipeIds = getByRecipeId(recipeId).stream()
                .map(Ingredient::getId)
                .collect(Collectors.toList());
        return entityManager.createQuery(
                "delete " +
                        "from Ingredient i " +
                        "where i.id in :recipeIds")
                .setParameter("recipeIds", recipeIds)
                .executeUpdate() != 0;
    }
}
