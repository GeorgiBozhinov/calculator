package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.model.dto.AdditionalIngredientsDTO;
import com.example.calculator.data.model.dto.ProductDTO;
import com.example.calculator.data.repository.IngredientRepository;
import com.example.calculator.data.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/uploads";

    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, IngredientRepository ingredientRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    public void addProduct(ProductDTO productDTO) {
        Double waxPriceCalc = calculatePrice(productDTO);

        productDTO.setPrice(waxPriceCalc);

        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        productRepository.save(productEntity);
    }


    public Integer jarQuantity(String jarType){
        IngredientEntity ingredientEntity = ingredientRepository.findByIngredientName(jarType);

        if(ingredientEntity != null){
            return ingredientEntity.getQuantity();
        }

        return 0;
    }

//    public List<ProductEntity> getAllProducts() {
//        List<ProductEntity> list = productRepository.findAll();
//        //list.forEach(l -> System.out.println("Image url: " + l.getImageName()));
//
//        return productRepository.findAll();
//
////        List<ProductEntity> allTenDollarProducts =
////                productRepository.findAllByPrice(, secondPageWithFiveElements);
//    }

    public Page<ProductEntity> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);


    }


    public List<ProductEntity> checkIfExistSuchProduct(String productName, String candleJar){
        return productRepository.findByCandleNameAndCandleJar(productName, candleJar);
    }


    private Double calculatePrice(ProductDTO productDTO){
        String waxType = productDTO.getWaxType();  //repeating
        int waxQuantity = productDTO.getWaxQuantity(); //repeating

        String wickType = productDTO.getCandleWick(); //repeating
        int wickSize = productDTO.getWickSize();  //repeating

        String jarName = productDTO.getCandleJar();
        IngredientEntity ingredientEntity = new IngredientEntity();

        ingredientEntity = ingredientRepository.findByIngredientName(waxType);
        String unitType = ingredientEntity.getUnitName();
        Double waxPrice = ingredientEntity.getPrice();
        Integer waxQuantityDB = ingredientEntity.getQuantity();

        Double ingredientPrice = 0.0;

        if(Objects.equals(unitType, "кг")){
            waxQuantityDB = waxQuantityDB * 1000;
        }

        ingredientEntity = ingredientRepository.findByIngredientName(wickType);
        Double wickPrice = ingredientEntity.getPrice();
        Integer wickSizeDB = ingredientEntity.getSize();

        ingredientEntity = ingredientRepository.findByIngredientName(jarName);

        ingredientPrice = waxPrice/(waxQuantityDB/waxQuantity);
        ingredientPrice += wickPrice/(wickSizeDB/wickSize);
        ingredientPrice += ingredientEntity.getPrice();

        String scentType = productDTO.getScentType();
        double scentQuantity = productDTO.getScentQuantity();
        ingredientEntity = ingredientRepository.findByIngredientName(scentType);
        Double scentPrice = ingredientEntity.getPrice();
        int scentQuantityDB = ingredientEntity.getQuantity();

        ingredientPrice += scentPrice/(scentQuantityDB/scentQuantity);

       List<String> additionalIngredients = productDTO.getAdditionalIngredients();

        if(additionalIngredients.size() >= 1){
            for (String ingredient : additionalIngredients) {
                String[] spllitted = ingredient.split("-");

                if(spllitted.length > 1){
                    ingredientEntity = ingredientRepository.findByIngredientName(spllitted[0]);
                    double temp = ingredientEntity.getPrice() * Integer.parseInt(spllitted[1]);
                    System.out.println(spllitted[0] + ": " + temp);
                    ingredientPrice += temp;
                }
            }
        }

        ingredientPrice = Math.round(ingredientPrice * 100.0) / 100.0;

        return ingredientPrice;
    }

    public void saveUploadedFile(MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            StringBuilder fileNames = new StringBuilder();
            String fileName = file.getOriginalFilename();

            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);

            fileNames.append(fileName);
            Files.write(fileNameAndPath, file.getBytes());

        }

    }

}
