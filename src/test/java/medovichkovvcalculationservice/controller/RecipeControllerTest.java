package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static medovichkovvcalculationservice.TestDataUtils.createRecipeDTO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class RecipeControllerTest {

    @Autowired
    private RecipeController recipeController;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private Model model;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void getAllUserRecipes() throws Exception {
        mockMvc.perform(get("/recipe"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipes"))
                .andExpect(view().name("recipe/all"));
    }

    @Test
    void getAllUserRecipesError() {
        when(recipeService.getAllRecipesForUser(anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(IllegalStateException.class, () -> recipeController.getAllUserRecipes(model));
    }

    @Test
    void getRecipe() throws Exception {
        when(recipeService.getRecipeForUser(anyLong(), anyLong())).thenReturn(createRecipeDTO());
        mockMvc.perform(get("/recipe/100"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/recipe"));
    }

    @Test
    void getRecipeError() {
        when(recipeService.getRecipeForUser(anyLong(), anyLong())).thenThrow(IllegalStateException.class);
        assertThrows(IllegalStateException.class, () -> recipeController.getRecipe(1L, model));
    }

    @Test
    void updateRecipe() throws Exception {
        when(recipeService.getByIdAndUser(anyLong(), anyLong())).thenReturn(new Recipe());
        mockMvc.perform(get("/recipe/update/100"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipeDto"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    void createRecipe() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipeDto"))
                .andExpect(view().name("recipe/recipeform"));
    }

    @Test
    void saveOrUpdate() throws Exception {
        when(recipeService.save(any())).thenReturn(new Recipe());
        mockMvc.perform(post("/recipe"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteRecipe() throws Exception {
        mockMvc.perform(get("/recipe/delete/100"))
                .andExpect(status().is3xxRedirection());
    }
}