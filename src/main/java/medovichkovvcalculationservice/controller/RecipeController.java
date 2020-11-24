package medovichkovvcalculationservice.controller;

import lombok.extern.slf4j.Slf4j;
import medovichkovvcalculationservice.SecurityUtils;
import medovichkovvcalculationservice.service.RecipeService;
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

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String getAllUserRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipesForUser(SecurityUtils.getCurrentUser()));
        return "recipe/all";
    }

    @GetMapping("/{" + RECIPE_ID_URL + "}")
    public String getRecipe(@PathVariable(RECIPE_ID_URL) Long recipeId, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeForUser(recipeId, SecurityUtils.getCurrentUser()));
        return "recipe/recipe";
    }

}
