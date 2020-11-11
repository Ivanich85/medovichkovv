package medovichkovvcalculationservice.controller;

import lombok.extern.slf4j.Slf4j;
import medovichkovvcalculationservice.SecurityUtils;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.service.DtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author ivand on 21.10.2020
 */
@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RecipeController {

    private static final String ALL_RECIPES_URL = "/all";
    private static final String RECIPE_ID_URL = "recipeId";

    private final DtoService dtoService;

    public RecipeController(DtoService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping(ALL_RECIPES_URL)
    public ResponseEntity<List<RecipeDTO>> getAllUserRecipes() {
        try {
            return ResponseEntity.ok(dtoService.getAllRecipesForUser(SecurityUtils.getCurrentUser()));
        } catch (DtoCreateException dce) {
            log.error(dce.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    @GetMapping("/recipe/{" + RECIPE_ID_URL + "}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable(RECIPE_ID_URL) Long recipeId) {
        try {
            return ResponseEntity.of(Optional.of(dtoService.getRecipeForUser(recipeId, SecurityUtils.getCurrentUser())));
        } catch (DtoCreateException dce) {
            log.error(dce.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RecipeDTO());
        }
    }

}
