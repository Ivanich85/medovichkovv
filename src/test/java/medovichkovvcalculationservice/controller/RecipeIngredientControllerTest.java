package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.TestDataUtils;
import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.service.ComponentService;
import medovichkovvcalculationservice.service.IngredientService;
import medovichkovvcalculationservice.service.RecipeIngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static medovichkovvcalculationservice.TestDataUtils.createRecipeIngredientDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecipeIngredientControllerTest {

    @Autowired
    private RecipeIngredientController recipeIngredientController;

    @MockBean
    private RecipeIngredientService recipeIngredientService;

    @MockBean
    private IngredientService ingredientService;

    @MockBean
    private ComponentService componentService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(recipeIngredientController).build();
    }

    @Test
    void create() throws Exception {
        when(ingredientService.getAllForUser(anyLong())).thenReturn(List.of(new IngredientDTO()));
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe-ingr/new?componentId=100&recipeId=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("recipeIngredientDTO", "ingredients", "recipeId"))
                .andExpect(MockMvcResultMatchers.view().name("recipeingredient/recipeingredientform"));
    }

    @Test
    void update() throws Exception {
        IngredientDTO expected = TestDataUtils.createIngredientDTO();
        RecipeIngredient expectedRI = TestDataUtils.createRecipeIngredient(TestDataUtils.EntityNumber.ONE);
        expectedRI.setComponent(TestDataUtils.createComponent(TestDataUtils.EntityNumber.ONE));
        Recipe recipe = expectedRI.getComponent().getRecipe();
        recipe.setId(1L);

        when(recipeIngredientService.getById(anyLong())).thenReturn(expectedRI);
        when(ingredientService.getAllForUser(anyLong())).thenReturn(List.of(expected));
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe-ingr/update/100"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("recipeIngredientDTO", "ingredients", "recipeId"))
                .andExpect(MockMvcResultMatchers.view().name("recipeingredient/recipeingredientform"));
    }

    @Test
    void saveOrUpdate() throws Exception {
        Component testComponent = TestDataUtils.createComponent(TestDataUtils.EntityNumber.ONE);
        when(componentService.getById(any())).thenReturn(testComponent);
        when(ingredientService.getByIdAndUser(any(), any())).thenReturn(TestDataUtils.createIngredient());
        mockMvc.perform(MockMvcRequestBuilders
                .post("/recipe-ingr")
                .flashAttr("ingredient", createRecipeIngredientDTO()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    void delete() throws Exception {
        doNothing().when(recipeIngredientService).delete(anyLong());
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe-ingr/delete/100?recipeId=1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}