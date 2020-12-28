package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.entity.Component;

import java.util.List;

public interface ComponentService {

    Component save(Component component);

    Component getById(Long componentId);

    ComponentDTO getDtoById(Long componentId);

    List<Component> getAllForRecipe(Long recipeId);

    void delete(Long componentId);

    void deleteAllForRecipe(Long recipeId);
}
