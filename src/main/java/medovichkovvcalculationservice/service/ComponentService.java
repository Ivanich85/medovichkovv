package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.entity.Component;

import java.util.List;

public interface ComponentService extends CrudService<Component, Long> {

    List<Component> getAllForRecipe(Long recipeId);

    boolean deleteAllForRecipe(Long recipeId);
}
