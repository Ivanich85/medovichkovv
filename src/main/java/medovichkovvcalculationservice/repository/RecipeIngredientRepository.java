package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.entity.RecipeIngredient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivand on 23.07.2020
 */
@Repository
@Transactional
public class RecipeIngredientRepository extends AbstractRepository {

    @Transactional(readOnly = true)
    public RecipeIngredient getById(Long ingredientId) {
        return entityManager.find(RecipeIngredient.class, ingredientId);
    }

    @Transactional(readOnly = true)
    public List<RecipeIngredient> getByComponentId(Long componentId) {
        return entityManager.createQuery(
                "select i from RecipeIngredient i " +
                        "where i.component.id = :componentId", RecipeIngredient.class)
                .setParameter("componentId", componentId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<RecipeIngredient> getByRecipeId(Long recipeId) {
        return entityManager.createQuery(
                "select i from RecipeIngredient i " +
                        "where i.component.recipe.id = :recipeId", RecipeIngredient.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    public RecipeIngredient save(RecipeIngredient ingredient) {
        if (ingredient.getId() == null) {
            entityManager.persist(ingredient);
            return ingredient;
        }
        return entityManager.merge(ingredient);
    }

    public void delete(Long ingredientId) {
        entityManager.createQuery(
                "delete " +
                        "from RecipeIngredient i " +
                        "where i.id = :ingredientId")
                .setParameter("ingredientId", ingredientId)
                .executeUpdate();
    }

    public void deleteAllForComponent(Long componentId) {
        entityManager.createQuery(
                "delete " +
                        "from RecipeIngredient i " +
                        "where i.component.id = :componentId")
                .setParameter("componentId", componentId)
                .executeUpdate();
    }

    public void deleteAllForComponents(List<Long> componentIds) {
        entityManager.createQuery(
                "delete " +
                        "from RecipeIngredient i " +
                        "where i.component.id in :componentIds")
                .setParameter("componentIds", componentIds)
                .executeUpdate();
    }

    public void deleteAllForRecipe(Long recipeId) {
        List<Long> recipeIds = getByRecipeId(recipeId).stream()
                .map(RecipeIngredient::getId)
                .collect(Collectors.toList());
        entityManager.createQuery(
                "delete " +
                        "from RecipeIngredient i " +
                        "where i.id in :recipeIds")
                .setParameter("recipeIds", recipeIds)
                .executeUpdate();
    }
}
