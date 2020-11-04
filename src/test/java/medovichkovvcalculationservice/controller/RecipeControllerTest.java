package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.service.DtoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static medovichkovvcalculationservice.service.TestCalculationDataUtils.createRecipeDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

class RecipeControllerTest extends AbstractControllerTest {

    @MockBean
    private DtoService dtoService;

    @Test
    void getAllUserRecipes() throws Exception {
        when(dtoService.getAllRecipesForUser(anyLong()))
                .thenReturn(List.of(createRecipeDTO(), createRecipeDTO()));
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                    .get("/recipes")
                    .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(OK.value(), mvcResult.getResponse().getStatus());
        RecipeDTO[] dtoArr = fromJson(mvcResult.getResponse().getContentAsString(), RecipeDTO[].class);
        assertEquals(2, dtoArr.length);
    }
}