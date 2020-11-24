package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import static medovichkovvcalculationservice.TestDataUtils.createRecipeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecipeControllerTest {

    private static final String ALL_RECIPES_NAME = "recipes";
    private static final String RECIPE_NAME = "recipe";

    @Autowired
    private RecipeController recipeController;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private Model model;

    @Test
    void getAllUserRecipes() {
        String modelName = recipeController.getAllUserRecipes(model);
        assertEquals("recipe/all", modelName);
        verify(recipeService).getAllRecipesForUser(anyLong());
        verify(model).addAttribute(eq(ALL_RECIPES_NAME), anyList());
    }

    @Test
    void getAllUserRecipesError() {
        when(recipeService.getAllRecipesForUser(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(IllegalStateException.class, () -> recipeController.getAllUserRecipes(model));
    }

    @Test
    void getRecipe() {
        when(recipeService.getRecipeForUser(anyLong(), anyLong())).thenReturn(createRecipeDTO());
        String modelName = recipeController.getRecipe(1L, model);
        assertEquals("recipe/recipe", modelName);
        verify(recipeService).getRecipeForUser(anyLong(), anyLong());
        verify(model).addAttribute(eq(RECIPE_NAME), any(RecipeDTO.class));
    }

    @Test
    void getRecipeError() {
        when(recipeService.getRecipeForUser(anyLong(), anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(IllegalStateException.class, () -> recipeController.getRecipe(1L, model));
    }
}