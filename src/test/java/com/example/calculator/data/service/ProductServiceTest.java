package com.example.calculator.data.service;

import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.model.dto.ProductDTO;
import com.example.calculator.data.repository.IngredientRepository;
import com.example.calculator.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductEntity productEntityTest = new ProductEntity() {{
        setId(id);
        setWickSize(3);
        setScentType("lemon");
        setScentQuantity(3);
        setCandleWick("normal");
        setCandleJar("regular_box");
        setWaxType("regular");
        setWaxQuantity(200);
        setCandleName("Number_One");
        setImageName("/images/uploads/imageOne.png");
    }};


    private IngredientEntity ingredientEntity =  new IngredientEntity() {{
        setIngredientName("regular_box");
        setIngredientType("wax");
        setQuantity(300);
        setSize(0);
        setPrice(11.0);
        setUnitName("кг");
    }};


    private long id = 1;

    @Mock
    private ProductRepository mockedProductRepository;

    private ProductService toTest;

    @Mock
    private IngredientRepository mockedIngredientRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        modelMapper = new ModelMapper();
        toTest = new ProductService(mockedProductRepository, mockedIngredientRepository, modelMapper);
    }


    /*
    TO TEST
    addProduct() - tested
    jarQuantity() - two cases - tested
    getAllProducts()
    checkIfExistSuchProduct() - tested
    calculatePrice() - used by addProduct
    saveUploadedFile()
     */

    @Test
    void addProduct_ShouldBeAdded() {
        List<ProductEntity> listProducts = new ArrayList<>();
        listProducts.add(productEntityTest);

       // this.mockedProductRepository.save(productEntityTest);
        System.out.println("Candle name: " + productEntityTest.getCandleName());
        when(this.mockedProductRepository.findByCandleNameAndCandleJar(productEntityTest.getCandleName(), productEntityTest.getCandleJar())).thenReturn(listProducts);

        List<ProductEntity> actualList = toTest.checkIfExistSuchProduct(productEntityTest.getCandleName(), productEntityTest.getCandleJar());
        System.out.println("List size: " + actualList.size());
        System.out.println(actualList.get(0).toString());

        for ( ProductEntity actualEntity: actualList ) {
            ProductEntity expectedEntity = listProducts.get(0);

            Assertions.assertEquals(actualEntity.getCandleName(), expectedEntity.getCandleName());
            Assertions.assertEquals(actualEntity.getWaxType(), expectedEntity.getWaxType());
            Assertions.assertEquals(actualEntity.getCandleJar(), expectedEntity.getCandleJar());
            Assertions.assertEquals(actualEntity.getWaxQuantity(), expectedEntity.getWaxQuantity());
            Assertions.assertEquals(actualEntity.getCandleWick(), expectedEntity.getCandleWick());
            Assertions.assertEquals(actualEntity.getWickSize(), expectedEntity.getWickSize());
            Assertions.assertEquals(actualEntity.getScentType(), expectedEntity.getScentType());
            Assertions.assertEquals(actualEntity.getScentQuantity(), expectedEntity.getScentQuantity());
            Assertions.assertEquals(actualEntity.getPrice(), expectedEntity.getPrice());
            Assertions.assertEquals(actualEntity.getImageName(), expectedEntity.getImageName());
        }
    }

    @Test
    void jarQuantity_ShouldReturnIt_WhenExist_ShouldReturnIntegerValueHigherThanZero(){
        when(this.mockedIngredientRepository.findByIngredientName(productEntityTest.getCandleJar())).thenReturn(ingredientEntity);
        int responseValue = toTest.jarQuantity(productEntityTest.getCandleJar());
        System.out.println("Returned quantity: " + responseValue);
        System.out.println("Expected quantity: " + ingredientEntity.getQuantity());
        Assertions.assertEquals(ingredientEntity.getQuantity(), responseValue);
    }

    @Test
    void jarQuantity_ShouldReturnZero_WhenThereAreNoSuchEntity(){

        int expectedValue = 0;
        int responseValue = toTest.jarQuantity(productEntityTest.getCandleJar());

        System.out.println("Returned quantity: " + responseValue);
        System.out.println("Expected quantity: " + expectedValue);

        Assertions.assertEquals(expectedValue, responseValue);
    }


//    @Test
//    void getAllProducts_ShouldReturn_WhenExist(){
//
//    }

    @Test
    void checkIfSuchProductExist_ShouldReturnIt_WhenExist(){
        when(this.mockedProductRepository.findByCandleNameAndCandleJar(productEntityTest.getCandleName(), productEntityTest.getCandleJar()))
                .thenReturn(List.of(productEntityTest));
        List<ProductEntity> actualEntityList = toTest.checkIfExistSuchProduct(productEntityTest.getCandleName(), productEntityTest.getCandleJar());
        System.out.println(actualEntityList.toString());
        ProductEntity productEntity = actualEntityList.get(0);

        Assertions.assertEquals(productEntityTest.getCandleName(), productEntity.getCandleName());
        Assertions.assertEquals(productEntityTest.getPrice(), productEntity.getPrice());
        Assertions.assertEquals(productEntityTest.getScentQuantity(), productEntity.getScentQuantity());
        Assertions.assertEquals(productEntityTest.getWaxQuantity(), productEntity.getWaxQuantity());
        Assertions.assertEquals(productEntityTest.getWickSize(), productEntity.getWickSize());
        Assertions.assertEquals(productEntityTest.getCandleWick(), productEntity.getCandleWick());
        Assertions.assertEquals(productEntityTest.getId(), productEntity.getId());
        Assertions.assertEquals(productEntityTest.getWaxType(), productEntity.getWaxType());
    }

    @Test
    void calculatePrice_WhenAllIngredientsAreFound_ShouldCalculateThePrice() {

        IngredientEntity entityJar = new IngredientEntity();
        entityJar.setIngredientName("jar1");
        entityJar.setPrice(10.0);
        entityJar.setSize(0);
        entityJar.setQuantity(200);
        entityJar.setUnitName("гр");
        entityJar.setIngredientType("jar");

        IngredientEntity entityScent = new IngredientEntity();
        entityScent.setIngredientName("aromat1");
        entityScent.setPrice(5.0);
        entityScent.setSize(0);
        entityScent.setQuantity(10);
        entityScent.setUnitName("мл");
        entityScent.setIngredientType("scent");

        IngredientEntity entityWick = new IngredientEntity();
        entityWick.setIngredientName("wick1");
        entityWick.setPrice(6.0);
        entityWick.setSize(10);
        entityWick.setQuantity(0);
        entityWick.setUnitName("см");
        entityWick.setIngredientType("wick");

        IngredientEntity entityWax = new IngredientEntity();
        entityWax.setIngredientName("wax-normal");
        entityWax.setPrice(10.0);
        entityWax.setSize(0);
        entityWax.setQuantity(1000);
        entityWax.setUnitName("гр");
        entityWax.setIngredientType("wax");

        IngredientEntity entityCinnamonOthers = new IngredientEntity();
        entityCinnamonOthers.setIngredientName("Пръчка");
        entityCinnamonOthers.setPrice(10.0);
        entityCinnamonOthers.setSize(0);
        entityCinnamonOthers.setQuantity(1);
        entityCinnamonOthers.setUnitName("бр");
        entityCinnamonOthers.setIngredientType("others");

        ProductDTO productDTO = new ProductDTO();

        productDTO.setWickSize(5);
        productDTO.setScentType("Шоколад");
        productDTO.setWaxQuantity(110);
        productDTO.setCandleJar("Бурканче 130 мл");
        productDTO.setWaxType("Соев восък");
        productDTO.setCandleWick("фитил ф4-5");
        productDTO.setScentQuantity(5.0);
        productDTO.setAdditionalIngredients(List.of("Пръчка-1"));

        when(mockedIngredientRepository.findByIngredientName(productDTO.getWaxType())).thenReturn(entityWax);
        when(mockedIngredientRepository.findByIngredientName(productDTO.getCandleJar())).thenReturn(entityJar);
        when(mockedIngredientRepository.findByIngredientName(productDTO.getCandleWick())).thenReturn(entityWick);
        when(mockedIngredientRepository.findByIngredientName(productDTO.getScentType())).thenReturn(entityScent);
        when(mockedIngredientRepository.findByIngredientName("Пръчка")).thenReturn(entityCinnamonOthers);

        Double actualPrice = toTest.calculatePrice(productDTO);
        System.out.println("actual price is: " + actualPrice);

        //assert
        Assertions.assertEquals(26.61, actualPrice);
    }

}
