package medovichkovvcalculationservice.controller;

/**
 * @author ivand on 20.12.2020
 */
public abstract class ControllerUtils {

    static final String REDIRECT_RECIPE = "redirect:/recipe";

    static String redirectToRecipe(Long recipeId) {
        return recipeId == null ? REDIRECT_RECIPE : REDIRECT_RECIPE + "/" + recipeId;
    }
}
