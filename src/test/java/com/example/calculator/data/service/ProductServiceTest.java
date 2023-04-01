package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.repository.IngredientRepository;
import com.example.calculator.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.SQLOutput;
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
    checkIfExistSuchProduct()
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

    @Test
    public void calculatePrice_WhenAllIngredientsAreFound_ShouldCalculateThePrice() {

        this.productEntityTest = new ProductEntity() {{
            setCandleName("Test");
            setWaxType("Восък");
            setCandleJar("Буркан1");
            setWaxQuantity(100);
            setCandleWick("ф5");
            setWickSize(5);
            setScentType("лимон");
            setScentQuantity(3);
            setId(1);
        }};

        when(this.mockedProductRepository.findByCandleName("Test"))
                .thenReturn((List<ProductEntity>) this.productEntityTest);

        ProductService productService = new ProductService(this.mockedProductRepository, this.mockedIngredientRepository, modelMapper);
        ProductEntity expected = this.productEntityTest;

        List<ProductEntity> actual = productService.checkIfExistSuchProduct("Test", "Буркан1");

        Assertions.assertEquals(actual.size(), 1);
    }

}
