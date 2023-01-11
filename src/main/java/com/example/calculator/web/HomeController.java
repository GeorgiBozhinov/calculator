package com.example.calculator.web;

import com.example.calculator.data.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getIndex(){
        return "home/index.html";
    }

//    @GetMapping("/")
//    public String getIndex(Model model,
//                           @PageableDefault(
//                                   sort = "price",
//                                   direction = Sort.Direction.ASC,
//                                   page = 0,
//                                   size = 5) Pageable pageable){
//
//        if(!model.containsAttribute("products")){
//            model.addAttribute("products", productService.getAllProducts());
//        }
//
//        return "home/index.html";
//    }

    @GetMapping("/home")
    public String getHome(Model model) {
        if(!model.containsAttribute("products")){
            model.addAttribute("products", productService.getAllProducts());
        }


        return "home/home.html";
    }

}
