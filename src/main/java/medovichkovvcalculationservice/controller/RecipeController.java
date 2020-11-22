package medovichkovvcalculationservice.controller;

import lombok.extern.slf4j.Slf4j;
import medovichkovvcalculationservice.SecurityUtils;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.service.DtoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ivand on 21.10.2020
 */
@Controller
@RequestMapping(value = "/recipe")
@Slf4j
public class RecipeController {

    private static final String RECIPE_ID_URL = "recipeId";

    private final DtoService dtoService;

    public RecipeController(DtoService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping
    public String getAllUserRecipes(Model model) {
        try {
            model.addAttribute("recipes", dtoService.getAllRecipesForUser(SecurityUtils.getCurrentUser()));
            return "recipe/all";
        } catch (DtoCreateException dce) {
            log.error(dce.toString());
            return null;
        }
    }

    @GetMapping("/{" + RECIPE_ID_URL + "}")
    public String getRecipe(@PathVariable(RECIPE_ID_URL) Long recipeId, Model model) {
        try {
            model.addAttribute("recipe", dtoService.getRecipeForUser(recipeId, SecurityUtils.getCurrentUser()));
            return "recipe/recipe";
        } catch (DtoCreateException dce) {
            log.error(dce.toString());
            return null;
        }
    }

}
