package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entities.Component;

import java.util.List;

public interface ComponentService {

    Component getById(Long componentId);

    List<Component> getAllForRecipe(Long recipeId);

    Component save(Component component);

    boolean delete(Long componentId);

    boolean deleteAllForRecipe(Long recipeId);
}
