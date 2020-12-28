package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.dto.RecipeIngredientDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Ingredient;
import medovichkovvcalculationservice.entity.RecipeIngredient;
import medovichkovvcalculationservice.service.ComponentService;
import medovichkovvcalculationservice.service.IngredientService;
import medovichkovvcalculationservice.service.RecipeIngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static medovichkovvcalculationservice.SecurityUtils.getCurrentUser;
import static medovichkovvcalculationservice.controller.ControllerUtils.redirectToRecipe;
import static medovichkovvcalculationservice.dto.DtoUtils.createFromRecipeIngredient;

/**
 * @author ivand on 16.12.2020
 */
@Controller
@RequestMapping(value = "/recipe-ingr")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;
    private final IngredientService ingredientService;
    private final ComponentService componentService;

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService,
                                      IngredientService ingredientService,
                                      ComponentService componentService) {
        this.recipeIngredientService = recipeIngredientService;
        this.ingredientService = ingredientService;
        this.componentService = componentService;
    }

    @GetMapping("new")
    public String create(@RequestParam("componentId") Long componentId,
                         @RequestParam("recipeId") Long recipeId, Model model) {
        RecipeIngredientDTO ingredientDTO = new RecipeIngredientDTO();
        ingredientDTO.setComponentId(componentId);
        model.addAttribute("recipeIngredientDTO", ingredientDTO);
        model.addAttribute("ingredients", ingredientService.getAllForUser(getCurrentUser()));
        model.addAttribute("recipeId", recipeId);
        return "recipeingredient/recipeingredientform";
    }

    @GetMapping("update/{ingredient}")
    public String update(@PathVariable("ingredient") Long ingredientId, Model model) {
        RecipeIngredient recipeIngredient = recipeIngredientService.getById(ingredientId);
        RecipeIngredientDTO ingredientDTO = createFromRecipeIngredient(recipeIngredient);
        model.addAttribute("recipeIngredientDTO", ingredientDTO);
        model.addAttribute("ingredients", ingredientService.getAllForUser(getCurrentUser()));
        model.addAttribute("recipeId", recipeIngredient.getComponent().getRecipe().getId());
        return "recipeingredient/recipeingredientform";
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute("ingredient") RecipeIngredientDTO ingredientDTO) {
        RecipeIngredient ingredient = fromDto(ingredientDTO);
        recipeIngredientService.save(ingredient);
        return redirectToRecipe(ingredient.getComponent().getRecipe().getId());
    }

    @GetMapping("delete/{ingredient}")
    public String delete(@PathVariable("ingredient") Long ingredientId,
                         @RequestParam("recipeId") Long recipeId) {
        recipeIngredientService.delete(ingredientId);
        return redirectToRecipe(recipeId);
    }

    private RecipeIngredient fromDto(RecipeIngredientDTO recipeIngredientDTO) {
        RecipeIngredient recipeIngredient = recipeIngredientDTO.getId() != null ?
                recipeIngredientService.getById(recipeIngredientDTO.getId()) :
                new RecipeIngredient();

        Long componentId = recipeIngredientDTO.getComponentId();
        Component currentComponent = componentService.getById(componentId);
        if (currentComponent == null) {
            throw new IllegalStateException(String.format("Component for id %s is null", componentId));
        }
        recipeIngredient.setComponent(currentComponent);
        recipeIngredient.setId(recipeIngredientDTO.getId());
        Long ingredientId = recipeIngredientDTO.getIngredientDTO().getId();
        Ingredient currentIngredient = ingredientService.getByIdAndUser(ingredientId, getCurrentUser());
        if (currentIngredient == null) {
            throw new IllegalStateException(String.format("Ingredient for id %s is null", ingredientId));
        }
        recipeIngredient.setIngredient(currentIngredient);
        recipeIngredient.setQuantity(recipeIngredientDTO.getQuantity());
        return recipeIngredient;
    }
}
