package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class IngredientControllerTest {

    @Autowired
    private IngredientController ingredientController;

    @MockBean
    private IngredientService ingredientService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/ingredient"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredientsDto"))
                .andExpect(view().name("ingredient/all"));
    }

    @Test
    void createNewIngredient() throws Exception {
        mockMvc.perform(get("/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredientDTO"))
                .andExpect(view().name("ingredient/ingredientform"));
    }

    @Test
    void updateIngredient() throws Exception {
        when(ingredientService.getByIdAndUser(any(), any())).thenReturn(new IngredientDTO());
        mockMvc.perform(get("/ingredient/update/100"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ingredientDTO"))
                .andExpect(view().name("ingredient/ingredientform"));
    }

    @Test
    void deleteIngredient() throws Exception {
        when(ingredientService.getByIdAndUser(any(), any())).thenReturn(new IngredientDTO());
        mockMvc.perform(get("/ingredient/delete/100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("ingredientDTO"))
                .andExpect(view().name("redirect:/ingredient"));
    }

    @Test
    void deleteIngredientNotFound() throws Exception {
        mockMvc.perform(get("/ingredient/delete/100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeDoesNotExist("ingredientDTO"))
                .andExpect(view().name("redirect:/ingredient"));
    }

    @Test
    void saveOrUpdate() throws Exception {
        mockMvc.perform(post("/ingredient"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/ingredient"));
    }
}