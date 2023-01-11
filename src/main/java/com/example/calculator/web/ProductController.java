package com.example.calculator.web;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.model.dto.ProductDTO;
import com.example.calculator.data.service.IngredientService;
import com.example.calculator.data.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.example.calculator.configs.StringConstants.PRODUCT_EXIST;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final IngredientService ingredientService;

    public ProductController(ProductService productService, IngredientService ingredientService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
    }

    @ModelAttribute("productModel")
    public ProductDTO productDTO() {
        return new ProductDTO();
    }


    @GetMapping("/add")
    public String getAddProductPage(Model model) {

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

        return "views/add_product.html";
    }

    @GetMapping("/succ")
    public String getSuccessPage() {

        //If the request is coming from the index page, redirect to it.
        //When the user add product and then leave his device and after some time use it again if it is on /product/succ on refresh will give him an error
        return "views/result_product.html";
    }


    @PostMapping("/add")
    public String addProduct(@Valid ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productModel", productDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel",
                    bindingResult);

            return "redirect:/product/add";
        }

        List<ProductEntity> productEntityList = productService.checkIfExistSuchProduct(productDTO.getCandleName(), productDTO.getCandleJar());

        if(productEntityList.isEmpty()){
            String fileName = "/images/uploads/" + multipartFile.getOriginalFilename();

            productService.saveUploadedFile(multipartFile);
            productDTO.setImageName(fileName);
            productService.addProduct(productDTO);

            redirectAttributes.addFlashAttribute("productModelAdded", productDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModelAdded",
                    bindingResult);

            return "redirect:/product/succ";
        }

        ObjectError error = new ObjectError("globalError", PRODUCT_EXIST);
        bindingResult.addError(error);

        redirectAttributes.addFlashAttribute("productModel", productDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productModel",
                    bindingResult);

        return "redirect:/product/add";

    }

}
