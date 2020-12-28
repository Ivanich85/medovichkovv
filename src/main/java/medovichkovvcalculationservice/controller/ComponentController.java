package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.SecurityUtils;
import medovichkovvcalculationservice.dto.ComponentDTO;
import medovichkovvcalculationservice.entity.Component;
import medovichkovvcalculationservice.entity.Recipe;
import medovichkovvcalculationservice.service.ComponentService;
import medovichkovvcalculationservice.service.RecipeIngredientService;
import medovichkovvcalculationservice.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static medovichkovvcalculationservice.controller.ControllerUtils.redirectToRecipe;

/**
 * @author ivand on 08.12.2020
 */

@Controller
@RequestMapping("/component")
public class ComponentController {

    private final ComponentService componentService;
    private final RecipeService recipeService;
    private final RecipeIngredientService recipeIngredientService;

    public ComponentController(ComponentService componentService,
                               RecipeService recipeService,
                               RecipeIngredientService recipeIngredientService) {
        this.componentService = componentService;
        this.recipeService = recipeService;
        this.recipeIngredientService = recipeIngredientService;
    }

    @GetMapping("/new")
    public String createComponent(@RequestParam("recipeId") Long recipeId, Model model) {
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setRecipeDtoId(recipeId);
        model.addAttribute("componentDTO", componentDTO);
        return "component/componentform";
    }

    @GetMapping("/update/{componentId}")
    public String updateComponent(@PathVariable("componentId") Long componentId, Model model) {
        model.addAttribute("componentDTO", componentService.getDtoById(componentId));
        return "component/componentform";
    }

    @GetMapping("/delete/{id}")
    public String deleteComponent(@PathVariable("id") Long componentId,
                                  @RequestParam("recipeId") Long recipeId) {
        componentService.delete(componentId);
        return redirectToRecipe(recipeId);
    }

    @PostMapping
    public String saveComponent(@ModelAttribute ComponentDTO componentDTO) {
        componentService.save(fromDto(componentDTO));
        return redirectToRecipe(componentDTO.getRecipeDtoId());
    }

    private Component fromDto(ComponentDTO componentDTO) {
        Long componentId = componentDTO.getId();
        Component component = componentService.getById(componentId);
        if (component == null) {
            component = new Component();
            Recipe currentRecipe = recipeService.getByIdAndUserWithComponents(componentDTO.getRecipeDtoId(), SecurityUtils.getCurrentUser());
            component.setRecipe(currentRecipe);
        }
        component.setName(componentDTO.getName());
        component.setQuantity(componentDTO.getQuantity());
        component.setRecipeIngredients(recipeIngredientService.getByComponentId(componentId));
        component.setType(componentDTO.getType());
        return component;
    }
}
