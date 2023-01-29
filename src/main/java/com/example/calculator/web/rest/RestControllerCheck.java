package com.example.calculator.web.rest;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.model.dto.IngredientDTO;
import com.example.calculator.data.model.dto.ProductDTO;
import com.example.calculator.data.service.IngredientService;
import com.example.calculator.data.service.ProductService;
import com.example.calculator.data.service.imagesFolder.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.example.calculator.configs.StringConstants.*;

@RestController
@RequestMapping("/api")
public class RestControllerCheck {

    private final ProductService productService;

    private final IngredientService ingredientService;

    private final ImageService imageService;

    public RestControllerCheck(ProductService productService, IngredientService ingredientService, ImageService imageService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
        this.imageService = imageService;
    }

    @GetMapping("/check")
    public ResponseEntity<?> getIngredient(@RequestParam(name = "jarTpe") String jarTpe, @RequestParam(name = "waxQuantity") int waxQuantity) {

        if (jarTpe.isEmpty()) {
            return new ResponseEntity<>(NO_JAR, HttpStatus.PRECONDITION_REQUIRED);
        }

        Integer jarQuantity = productService.jarQuantity(jarTpe);
        if (jarQuantity == 0) {
            return new ResponseEntity<>(NOT_FOUND_JAR, HttpStatus.PRECONDITION_FAILED);
        }
        if (waxQuantity < 1) {
            return new ResponseEntity<>(NO_WAX, HttpStatus.PRECONDITION_REQUIRED);
        }
        if (waxQuantity > jarQuantity) {
            return new ResponseEntity<>(String.format(NEEDED_WAX, waxQuantity, jarQuantity), HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<>(String.format(NEEDED_WAX, waxQuantity, "smaller", jarQuantity), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateIngredient(@PathVariable("id") Long id, @RequestBody IngredientDTO ingredientDTO) {

        ingredientDTO.setId(id);
        ingredientService.updateIngredient(ingredientDTO, id);
        return new ResponseEntity<>(String.format(UPDATED, ingredientDTO.getIngredientName()), HttpStatus.OK);
    }

    /**
     * @param id - It represents the id of the record if such exist // TODO should be cached
     * @return Return two types of messages, one for success delete and one informational in case the row/record is not found
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {

        if (ingredientService.findIngredient(id).isPresent()) {
            ingredientService.deleteIngredientById(id);
            return new ResponseEntity<>(String.format(DELETED_OK, id), HttpStatus.OK);
        }
        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    /**
     * @return Return all candle jars if such are found
     */
    @GetMapping("/getAllJars")
    public ResponseEntity<?> getCandlesJars() {

        List jarList = ingredientService.findByIngredientType("scent");
        if (!jarList.isEmpty()) {
            return new ResponseEntity<>(jarList, HttpStatus.OK);
        }
        return new ResponseEntity<>(NO_JARS_FOUND, HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
//        productDTO.setImageName("/test");
        List<ProductEntity> productEntityList = productService.checkIfExistSuchProduct(productDTO.getCandleName(), productDTO.getCandleJar());
        if (productEntityList.isEmpty()) {
            productService.addProduct(productDTO);
            System.out.println(productDTO);
            return new ResponseEntity<>("Added new product: " + productDTO.getCandleName() + ", " + productDTO.getCandleJar(), HttpStatus.OK);
        }
        //With thymeleaf have to be returned bindingError and form be fullfilled
        return new ResponseEntity<>("Already exist product with product name: " + productDTO.getCandleName(), HttpStatus.PRECONDITION_FAILED);
    }

}

