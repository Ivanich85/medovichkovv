package medovichkovvcalculationservice.service;

import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.error.CalculationError;
import medovichkovvcalculationservice.repository.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DtoServiceTest extends AbstractTest {

    @Autowired
    private DtoService dtoService;

    @Mock
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
                () -> dtoService.recalculateRecipe(null, null, BigDecimal.ONE, null));
    }

    @Test
    void recalculateRecipe() {
        //doReturn(TestCalculationDataUtils.createRecipe()).when(recipeRepository).getByIdAndUserWithComponents(1L, 2L);
        when(recipeRepository.getByIdAndUserWithComponents(any(Long.class), any(Long.class))).thenReturn(TestCalculationDataUtils.createRecipe());
        //when(recipeRepository.getByIdAndUserWithComponents(any(), any())).thenReturn(TestCalculationDataUtils.createRecipe());
        RecipeDTO actualDTO = dtoService.recalculateRecipe(any(Long.class), any(Long.class), BigDecimal.valueOf(432.31), null);
        RecipeDTO expectedDTO = TestCalculationDataUtils.createRecipeDTO();
        expectedDTO.setCost(BigDecimal.valueOf(327.4));
        assertThat(actualDTO).isEqualToIgnoringGivenFields(expectedDTO, "componentDTOs");
    }
}