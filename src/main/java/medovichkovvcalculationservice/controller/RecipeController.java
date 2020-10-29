package medovichkovvcalculationservice.controller;

import medovichkovvcalculationservice.SecurityUtils;
import medovichkovvcalculationservice.dto.RecipeDTO;
import medovichkovvcalculationservice.exception.DtoCreateException;
import medovichkovvcalculationservice.service.DtoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * @author ivand on 21.10.2020
 */
@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

    private final DtoService dtoService;

    public RecipeController(DtoService dtoService) {
        this.dtoService = dtoService;
    }

    @GetMapping(value = "/recipes")
    @ResponseBody
    public List<RecipeDTO> getAllUserRecipes() {
        try {
            return ResponseEntity.of(Optional.of(dtoService.getAllRecipesForUser(SecurityUtils.getCurrentUser()))).getBody();
        } catch (DtoCreateException dce) {
            return null;
        }
    }

}
