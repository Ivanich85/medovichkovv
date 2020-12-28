package medovichkovvcalculationservice.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ControllerUtilsTest {

    private final static String REDIRECT_RECIPE = "redirect:/recipe";

    @Test
    void redirectToRecipeNull() {
        assertEquals(REDIRECT_RECIPE, ControllerUtils.redirectToRecipe(null));
    }

    @Test
    void redirectToRecipe() {
        assertEquals(REDIRECT_RECIPE + "/100", ControllerUtils.redirectToRecipe(100L));
    }
}