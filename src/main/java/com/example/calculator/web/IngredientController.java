package com.example.calculator.web;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.model.dto.IngredientDTO;
import com.example.calculator.data.service.ComponentService;
import com.example.calculator.data.service.IngredientService;
import com.example.calculator.data.service.UnitService;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;

import static com.example.calculator.configs.StringConstants.INGREDIENT_EXIST;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    private final UnitService unitService;

    private final ComponentService componentService;

    public IngredientController(IngredientService ingredientService, UnitService unitService, ComponentService componentService) {
        this.ingredientService = ingredientService;
        this.unitService = unitService;
        this.componentService = componentService;
    }

    @ModelAttribute("ingredientModel")
    public IngredientDTO ingredientDTO() {
        return new IngredientDTO();
    }

    @GetMapping("/add")
    public String getIngredientPage(Model model) {

        if (!model.containsAttribute("units")) {
            model.addAttribute("units", unitService.getAllUnits());
        }

        if (!model.containsAttribute("components")) {
            model.addAttribute("components", componentService.getAllComponents());
        }

        return "views/add_ingredient.html";
    }

    @GetMapping("/succ")
    public String getSuccessPage() {
        // When page is refreshed error is returned. Handle the error
        return "views/result_ingredient.html";
    }

    @GetMapping("/all")
    public String viewIngredientPage(Model model) {

        if (!model.containsAttribute("ingredients")) {
            model.addAttribute("ingredients", ingredientService.getAllIngredients());
        }

        return "views/view_ingredient.html";
    }

    @PostMapping("/add")
    public String addIngredient(@Valid IngredientDTO ingredientDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ingredientModel", ingredientDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientModel",
                    bindingResult);

            return "redirect:/ingredient/add";
        }

        IngredientEntity ingredientEntity = ingredientService.checkIfExistSuchIngredient(ingredientDTO.getIngredientName());

        if(Objects.isNull(ingredientEntity)){

            ingredientService.addIngredient(ingredientDTO);

            if (ingredientDTO.getSize() == null){
                ingredientDTO.setSize(0);
            }

            redirectAttributes.addFlashAttribute("ingredientAddedModel", ingredientDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddedModel",
                    bindingResult);

            return "redirect:/ingredient/succ";

        }

        ObjectError error = new ObjectError("globalError", INGREDIENT_EXIST);
        bindingResult.addError(error);

        redirectAttributes.addFlashAttribute("ingredientModel", ingredientDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientModel",
                bindingResult);

        return "redirect:/ingredient/add";

    }

}
