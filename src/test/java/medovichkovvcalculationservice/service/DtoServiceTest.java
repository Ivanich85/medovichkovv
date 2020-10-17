package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.error.CalculationError;
import medovichkovvcalculationservice.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
class DtoServiceTest {

    @Autowired
    private DtoService dtoService;

    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    void recalculateRecipeCakeAndSquareNull() {
        Assertions.assertThrows(CalculationError.class,
                () -> dtoService.recalculateRecipe(null, null, null, null),
                "New recipe square and cakes can`t be null");
    }

    @Test
    void recalculateRecipeCakeAndSquareNotNull() {
        Assertions.assertThrows(CalculationError.class,
                () -> dtoService.recalculateRecipe(null, null, BigDecimal.ONE, 15),
                "New recipe square and cakes not null. It`s ambiguity");
    }
    @Test
    void recalculateRecipeNotFound() {
        lenient().when(recipeRepository.getByIdAndUserWithComponents(any(), any())).thenReturn(null);
        Assertions.assertThrows(IllegalStateException.class,
                () -> dtoService.recalculateRecipe(1L, 2L, BigDecimal.ONE, null),
                "Recipe id 1 for user 2 not found");
    }

    @Test
    void recalculateRecipe_1_70_coef() {
        when(recipeRepository.getByIdAndUserWithComponents(anyLong(), anyLong())).thenReturn(TestCalculationDataUtils.createRecipe());
        RecipeDTO actualDTO = dtoService.recalculateRecipe(1L, 2L, BigDecimal.valueOf(432.31), null);
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        expectedDTO.setCost(BigDecimal.valueOf(327.4));
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "componentDTOs");
    }
}