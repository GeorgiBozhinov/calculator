package com.example.calculator.web;
import com.example.calculator.data.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String getIndex() {
//        throw new RuntimeException("test exception");
        return "home/index";
    }

    @GetMapping("/home")
    public String getHome(Model model,
                          @PageableDefault(
                                  sort = "candleName",
                                  direction = Sort.Direction.DESC,
                                  page = 0,
                                  size = 10) Pageable pageable) {

        if ( !model.containsAttribute("products") ) {
            model.addAttribute("products", productService.getAllProducts(pageable));
        }

        return "home/home";
    }

//    @GetMapping("/home")
//    public String getHome(Model model,
//                          @RequestParam("page") Optional<Integer> page,
//                          @RequestParam("size") Optional<Integer> size) {
//
//        int currentPage = page.orElse(1);
//        int pageSize = size.orElse(5);
//
//        Pageable pageable = PageRequest.of(0, 15);
//
//        if ( !model.containsAttribute("products") ) {
//            model.addAttribute("products", productService.getAllProducts(pageable));
//        }
//
//        return "home/home.html";
//    }

//    @GetMapping("/home")
//    public String getHome(Model model) {
//
//        Pageable pageable = PageRequest.of(0, 15);
//
//        if ( !model.containsAttribute("products") ) {
//            model.addAttribute("products", productService.getAllProducts(pageable));
//        }
//
//        return "home/home.html";
//    }
}
