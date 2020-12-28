package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.enums.PrivacyType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author ivand on 12.07.2020
 */

@Repository
@Transactional
public class RecipeRepository extends AbstractRepository {

    @Transactional(readOnly = true)
    public Recipe getByIdAndUser(Long recipeId, Long userId) {
        try {
            return entityManager.createQuery(
                    "select r from Recipe r " +
                            "where r.id = :recipeId " +
                            "and r.userId = :userId " +
                            "order by r.name", Recipe.class)
                    .setParameter("recipeId", recipeId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Recipe getByIdAndUserWithComponents(Long recipeId, Long userId) { ;
        try {
            return entityManager.createQuery(
                    "select r from Recipe r " +
                            "left join fetch r.components c " +
                            "where r.id = :recipeId and r.userId = :userId " +
                            "order by c.name ", Recipe.class)
                    .setParameter("recipeId", recipeId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Recipe> getAllForCurrentUser(Long userId) {
        return entityManager.createQuery(
                "select r " +
                        "from Recipe r " +
                        "where r.userId = :userId " +
                        "order by r.name", Recipe.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public List<Recipe> getAll() {
        return entityManager.createQuery(
                "select r " +
                        "from Recipe r " +
                        "where r.privacyType = :privacyType " +
                        "order by r.name ", Recipe.class)
                .setParameter("privacyType", PrivacyType.ALL)
                .getResultList();
    }

    public Recipe save(Recipe recipe) {
        entityManager.persist(recipe);
        return recipe;
    }

    public void delete(Long recipeId, Long userId) {
        entityManager.createQuery(
                "delete " +
                        "from Recipe r " +
                        "where r.id = :recipeId and r.userId = :userId")
                .setParameter("recipeId", recipeId)
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
