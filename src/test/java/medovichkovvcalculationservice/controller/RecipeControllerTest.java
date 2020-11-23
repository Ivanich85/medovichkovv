package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.service.DtoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import static medovichkovvcalculationservice.TestDataUtils.createRecipeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RecipeControllerTest {

    private static final String ALL_RECIPES_NAME = "recipes";
    private static final String RECIPE_NAME = "recipe";

    @Autowired
    private RecipeController recipeController;

    @MockBean
    private DtoService dtoService;

    @MockBean
    private Model model;

    @Test
    void getAllUserRecipes() throws Exception {
        String modelName = recipeController.getAllUserRecipes(model);
        assertEquals("recipe/all", modelName);
        verify(dtoService).getAllRecipesForUser(anyLong());
        verify(model).addAttribute(eq(ALL_RECIPES_NAME), anyList());
    }

    @Test
    void getAllUserRecipesError() throws Exception {
        when(dtoService.getAllRecipesForUser(anyLong())).thenThrow(DtoCreateException.class);
        String modelName = recipeController.getAllUserRecipes(model);
        assertNull(modelName);
        verify(dtoService).getAllRecipesForUser(anyLong());
        verify(model, times(0)).addAttribute(eq(ALL_RECIPES_NAME), anyList());
    }

    @Test
    void getRecipe() throws Exception {
        when(dtoService.getRecipeForUser(anyLong(), anyLong())).thenReturn(createRecipeDTO());
        String modelName = recipeController.getRecipe(1L, model);
        assertEquals("recipe/recipe", modelName);
        verify(dtoService).getRecipeForUser(anyLong(), anyLong());
        verify(model).addAttribute(eq(RECIPE_NAME), any(RecipeDTO.class));
    }

    @Test
    void getRecipeError() throws Exception {
        when(dtoService.getRecipeForUser(anyLong(), anyLong())).thenThrow(DtoCreateException.class);
        String modelName = recipeController.getRecipe(1L, model);
        assertNull(modelName);
        verify(dtoService).getRecipeForUser(anyLong(), anyLong());
        verify(model, times(0)).addAttribute(eq(RECIPE_NAME), any(RecipeDTO.class));
    }
}