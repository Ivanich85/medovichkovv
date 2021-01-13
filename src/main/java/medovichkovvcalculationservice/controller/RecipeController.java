package medovichkovvcalculationservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import medovichkovvcalculationservice.dto.DtoUtils;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static medovichkovvcalculationservice.SecurityUtils.getCurrentUser;
import static medovichkovvcalculationservice.controller.ControllerUtils.redirectToRecipe;

/**
 * @author ivand on 21.10.2020
 */
@Api(description = "Thymeleaf CRUD controller for recipes")
@Controller
@RequestMapping(value = "/recipe")
@Slf4j
public class RecipeController {

    private static final String RECIPE_ID_URL = "recipeId";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @ApiOperation(value = "Return all user`s recipes")
    @GetMapping
    public String getAllUserRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipesForUser(getCurrentUser()));
        return "recipe/all";
    }

    @ApiOperation(value = "Open current recipe")
    @GetMapping("/{" + RECIPE_ID_URL + "}")
    public String getRecipe(@PathVariable(RECIPE_ID_URL) Long recipeId, Model model) {
        RecipeDTO recipeDTO = recipeService.getRecipeForUser(recipeId, getCurrentUser());
        model.addAttribute("recipe", recipeDTO);
        return "recipe/recipe";
    }

    @ApiOperation(value = "Produces form for current recipe update")
    @GetMapping("/update/{id}")
    public String updateRecipe(@PathVariable("id") Long recipeId, Model model) {
        model.addAttribute("recipeDto", DtoUtils.createFromRecipe(recipeService.getByIdAndUser(recipeId, getCurrentUser())));
        return "recipe/recipeform";
    }

    @ApiOperation(value = "Produces form for creation new recipe")
    @GetMapping("/new")
    public String createRecipe(Model model) {
        model.addAttribute("recipeDto", new RecipeDTO());
        return "recipe/recipeform";
    }

    @ApiOperation(value = "Save (or update) new (or current) recipe")
    @PostMapping
    public String saveOrUpdate(@ModelAttribute RecipeDTO recipeDTO) {
        Recipe savedRecipe = recipeService.save(fromDto(recipeDTO));
        return redirectToRecipe(savedRecipe.getId());
    }

    @ApiOperation(value = "Delete current recipe, recipe components and ingredients ")
    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") Long recipeId) {
        recipeService.delete(recipeId, getCurrentUser());
        return redirectToRecipe(null);
    }

    private Recipe fromDto(RecipeDTO recipeDTO) {
        Recipe currentRecipe = recipeService.getByIdAndUser(recipeDTO.getId(), getCurrentUser());
        if (currentRecipe == null) {
            currentRecipe = new Recipe();
            currentRecipe.setCreationDate(LocalDateTime.now());
        }
        Long userId = recipeDTO.getUserId();
        currentRecipe.setUserId(userId != null ? userId : getCurrentUser());
        currentRecipe.setName(recipeDTO.getName());
        currentRecipe.setFavorite(recipeDTO.isFavorite());
        currentRecipe.setCakes(recipeDTO.getCakes());
        currentRecipe.setSquare(recipeDTO.getSquare());
        return currentRecipe;
    }
}
