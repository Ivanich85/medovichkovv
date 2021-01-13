package medovichkovvcalculationservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(description = "Thymeleaf CRUD controller for common ingredients")
@Controller
@RequestMapping(value = "/ingredient")
@Slf4j
public class IngredientController {

    private static final String REDIRECT_INGREDIENT = "redirect:/ingredient";

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @ApiOperation(value = "Return all user`s ingredients")
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("ingredientsDto", ingredientService.getAllForUser(SecurityUtils.getCurrentUser()));
        return "ingredient/all";
    }

    @ApiOperation(value = "Produces form for creation new ingredient")
    @GetMapping("/new")
    public String createNewIngredient(Model model) {
        model.addAttribute("ingredientDTO", new IngredientDTO());
        return "ingredient/ingredientform";
    }

    @ApiOperation(value = "Produces form for current ingredient update")
    @GetMapping("/update/{id}")
    public String updateIngredient(@PathVariable("id") Long ingredientId, Model model) {
        model.addAttribute("ingredientDTO", ingredientService.getDtoByIdAndUser(ingredientId, SecurityUtils.getCurrentUser()));
        return "ingredient/ingredientform";
    }

    @ApiOperation(value = "Delete current ingredient")
    @GetMapping("/delete/{id}")
    public String deleteIngredient(@PathVariable("id") Long ingredientId) {
        IngredientDTO ingredientDTO = ingredientService.getDtoByIdAndUser(ingredientId, SecurityUtils.getCurrentUser());
        if (ingredientDTO != null) {
            ingredientService.delete(ingredientId);
        }
        return REDIRECT_INGREDIENT;
    }

    @ApiOperation(value = "Save (or update) new (or current) ingredient")
    @PostMapping
    public String saveOrUpdate(@ModelAttribute IngredientDTO ingredientDTO) {
        ingredientDTO.setUserId(SecurityUtils.getCurrentUser());
        ingredientService.save(ingredientDTO);
        return REDIRECT_INGREDIENT;
    }

}
