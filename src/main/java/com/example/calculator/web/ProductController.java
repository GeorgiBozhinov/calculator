package com.example.calculator.web;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.model.dto.AdditionalIngredientsDTO;
import com.example.calculator.data.model.dto.ProductDTO;
import com.example.calculator.data.service.IngredientService;
import com.example.calculator.data.service.ProductService;
import com.example.calculator.data.service.imagesFolder.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.calculator.configs.StringConstants.IMAGE_SIZE;
import static com.example.calculator.configs.StringConstants.PRODUCT_EXIST;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private final IngredientService ingredientService;

    private final ImageService imageService;

    public ProductController(ProductService productService, IngredientService ingredientService, ImageService imageService) {

        this.productService = productService;
        this.ingredientService = ingredientService;
        this.imageService = imageService;
    }

    @ModelAttribute("productModel")
    public ProductDTO productDTO() {

        return new ProductDTO();
    }

    @GetMapping("/add")
    public String getAddProductPage(Model model) {
        ProductDTO productDTO = new ProductDTO();

        List numberOfOptions = new ArrayList();

        for(int i = 1; i <= 10; i++){
            numberOfOptions.add(i);
        }

        //System.out.println(numberOfOptions);
        addAttribute(model, "waxes", "wax");
        addAttribute(model, "jars", "jar");
        addAttribute(model, "scents", "scent");
        addAttribute(model, "wicks", "wick");
        addAttribute(model, "others", "others");


        //model.addAttribute("productModel", productDTO);
        model.addAttribute("options", numberOfOptions);
        //model.addAttribute("additionalIngredients", additionalIngredientsList);

        return "views/add_product.html";
    }

    @GetMapping("/succ")
    public String getSuccessPage() {

        //If the request is coming from the index page, redirect to it.
        //When the user add product and then leave his device and after some time use it again if it is on /product/succ on refresh will give him an error
        return "views/result_product.html";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductDTO productDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {

        //imageService.changeImageBackground();
        String test= "";

        if ( bindingResult.hasErrors() ) {
            addFlashAttribute(redirectAttributes, "productModel",  productDTO,  bindingResult);

            return "redirect:/product/add";
        }

        if ( imageService.checkImageSize(multipartFile.getSize()) ) {
            ObjectError error = new ObjectError("globalError", IMAGE_SIZE);
            bindingResult.addError(error);
            addFlashAttribute(redirectAttributes, "productModel",  productDTO,  bindingResult);

            return "redirect:/product/add";
        }

        List<ProductEntity> productEntityList = productService.checkIfExistSuchProduct(productDTO.getCandleName(), productDTO.getCandleJar());

        if ( productEntityList.isEmpty() ) {
            String fileName = "/images/uploads/" + multipartFile.getOriginalFilename();

            productService.saveUploadedFile(multipartFile);
            productDTO.setImageName(fileName);
            productService.addProduct(productDTO);

            addFlashAttribute(redirectAttributes, "productModelAdded",  productDTO,  bindingResult);
            return "redirect:/product/succ";
        }

        ObjectError error = new ObjectError("globalError", PRODUCT_EXIST);
        bindingResult.addError(error);
        addFlashAttribute(redirectAttributes, "productModel",  productDTO,  bindingResult);

        return "redirect:/product/add";
    }


    private void  addAttribute(Model model, String attributeName, String ingredientType){
        if ( !model.containsAttribute(attributeName) ) {
            model.addAttribute(attributeName, ingredientService.findByIngredientType(ingredientType));
        }
    }

    private void addFlashAttribute(RedirectAttributes redirectAttributes,
                                   String attributeName,
                                   ProductDTO productDTO,
                                   BindingResult bindingResult){

        redirectAttributes.addFlashAttribute(attributeName, productDTO);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + attributeName,
                bindingResult);
    }
}
