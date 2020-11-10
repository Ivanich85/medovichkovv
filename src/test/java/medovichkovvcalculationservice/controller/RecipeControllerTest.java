package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.service.DtoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.createRecipeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeControllerTest extends AbstractControllerTest {

    private final static String ALL_URL = "/all";
    private final static String RECIPE_URL = "/recipe/100";

    @MockBean
    private DtoService dtoService;

    @Test
    void getAllUserRecipes() throws Exception {
        when(dtoService.getAllRecipesForUser(anyLong()))
                .thenReturn(List.of(createRecipeDTO(), createRecipeDTO()));
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                    .get(ALL_URL)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RecipeDTO[] dtoArr = fromJson(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), RecipeDTO[].class);
        assertEquals(2, dtoArr.length);
    }

    @Test
    void getAllUserRecipesError() throws Exception {
        when(dtoService.getAllRecipesForUser(anyLong())).thenThrow(DtoCreateException.class);
        mockMvc.perform(MockMvcRequestBuilders
                .get(ALL_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void getRecipe() throws Exception {
        RecipeDTO expectedDto = createRecipeDTO();
        when(dtoService.getRecipeForUser(anyLong(), anyLong())).thenReturn(expectedDto);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(RECIPE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RecipeDTO dtoArr = fromJson(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), RecipeDTO.class);
        assertEquals(expectedDto, dtoArr);
    }

    @Test
    void getRecipeError() throws Exception {
        when(dtoService.getRecipeForUser(anyLong(), anyLong())).thenThrow(DtoCreateException.class);
        mockMvc.perform(MockMvcRequestBuilders
                .get(RECIPE_URL)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}