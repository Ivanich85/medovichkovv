package medovichkovvcalculationservice.controller;

import lombok.extern.slf4j.Slf4j;
import medovichkovvcalculationservice.SecurityUtils;
import medovichkovvcalculationservice.dto.IngredientDTO;
import medovichkovvcalculationservice.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author ivand on 24.11.2020
 */
@Controller
@RequestMapping(value = "/ingredient")
@Slf4j
public class IngredientController {

    private static final String REDIRECT_INGREDIENT = "redirect:/ingredient";

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("ingredientsDto", ingredientService.getAllForUser(SecurityUtils.getCurrentUser()));
        return "ingredient/all";
    }

    @GetMapping("/new")
    public String createNewIngredient(Model model) {
        model.addAttribute("ingredientDTO", new IngredientDTO());
        return "ingredient/ingredientform";
    }

    @GetMapping("/update/{id}")
    public String updateIngredient(@PathVariable("id") Long ingredientId, Model model) {
        model.addAttribute("ingredientDTO", ingredientService.getByIdAndUser(ingredientId, SecurityUtils.getCurrentUser()));
        return "ingredient/ingredientform";
    }

    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Long ingredientId, Model model) {
        IngredientDTO ingredientDTO = ingredientService.getByIdAndUser(ingredientId, SecurityUtils.getCurrentUser());
        if (ingredientDTO != null) {
            model.addAttribute("ingredientDTO", ingredientService.delete(ingredientId));
        }
        return REDIRECT_INGREDIENT;
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute IngredientDTO ingredientDTO) {
        ingredientDTO.setUserId(SecurityUtils.getCurrentUser());
        ingredientService.save(ingredientDTO);
        return REDIRECT_INGREDIENT;
    }

}
