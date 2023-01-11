package com.example.calculator.web;
import com.example.calculator.data.model.dto.CalculatorDTO;
import com.example.calculator.data.service.CalculatorService;
import com.example.calculator.data.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CalculatorController {

    private final IngredientService ingredientService;
    private final CalculatorService calculatorService;

    public CalculatorController(IngredientService ingredientService, CalculatorService calculatorService) {
        this.ingredientService = ingredientService;
        this.calculatorService = calculatorService;
    }

    @ModelAttribute("calculatorModel")
    public CalculatorDTO calculatorDTO() {
        return new CalculatorDTO();
    }


    @GetMapping("/calc")
    public String getPage(Model model){

        if (!model.containsAttribute("waxes")) {
            model.addAttribute("waxes", ingredientService.findByIngredientType("wax"));
        }

        if (!model.containsAttribute("jars")) {
            model.addAttribute("jars", ingredientService.findByIngredientType("jar"));
        }

        if (!model.containsAttribute("scents")) {
            model.addAttribute("scents", ingredientService.findByIngredientType("scent"));
        }

        if (!model.containsAttribute("wicks")) {
            model.addAttribute("wicks", ingredientService.findByIngredientType("wick"));
        }

        if (!model.containsAttribute("others")) {
            model.addAttribute("others", ingredientService.findByIngredientType("others"));
        }

        return "views/calculator.html";
    }


    @PostMapping("/calc")
    public String calculatorCandle(@Valid CalculatorDTO calculatorDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        // Think how to reuse the method - calculatePrice()
        // CalculatorDTO validations added - Test it
        // Add multiselect field to the html page that will hold additional ingredients which will be calculated per piece.
        // Copy the calculation logic from productService
       // System.out.println(calculatorDTO);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("calculatorModel", calculatorDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.calculatorModel",
                    bindingResult);

            return "redirect:/calc";
        }

        Double price = calculatorService.calcPrice(calculatorDTO);

        ObjectError error = new ObjectError("globalError", "Общата сума е: " + price + "лв.");
        bindingResult.addError(error);

        redirectAttributes.addFlashAttribute("calculatorModel", calculatorDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.calculatorModel",
                bindingResult);

        return "redirect:/calc";

    }


}
