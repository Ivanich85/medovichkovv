package medovichkovvcalculationservice.repository;

import medovichkovvcalculationservice.entity.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ivand on 12.08.2020
 */
@Repository
@Transactional
public class ComponentRepository extends AbstractRepository {
    @Transactional(readOnly = true)
    public Component getById(Long componentId) {
        return entityManager.find(Component.class, componentId);
    }

    @Transactional(readOnly = true)
    public List<Component> getAllForRecipe(Long recipeId) {
        return entityManager.createQuery(
                "select c " +
                        "from Component c " +
                        "where c.recipe.id = :recipeId " +
                        "order by c.name", Component.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    public Component save(Component component) {
        if (component.getId() == null) {
            entityManager.persist(component);
            return component;
        }
        return entityManager.merge(component);
    }

    public boolean delete(Long componentId) {
        return entityManager.createQuery(
                "delete " +
                        "from Component c " +
                        "where c.id = :componentId")
                .setParameter("componentId", componentId)
                .executeUpdate() != 0;
    }

    public boolean deleteAllForRecipe(Long recipeId) {
        return entityManager.createQuery(
                "delete " +
                        "from Component c " +
                        "where c.recipe.id = :recipeId")
                .setParameter("recipeId", recipeId)
                .executeUpdate() != 0;
    }
}
