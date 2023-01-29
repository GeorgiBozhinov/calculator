package com.example.calculator.data.service;

import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.base_entities.ProductEntity;
import com.example.calculator.data.repository.IngredientRepository;
import com.example.calculator.data.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductEntity productEntityTest;
    @Mock
    private ProductRepository mockedProductRepository;

    private ProductService toTest;

    private IngredientRepository ingredientRepository;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        toTest = new ProductService(mockedProductRepository, ingredientRepository, modelMapper);
    }


    /*
    TO TEST
    addProduct()
    jarQuantity()
    getAllProducts()
    checkIfExistSuchProduct()
    calculatePrice() - used by addProduct
    saveUploadedFile()
     */

    @Test
    public void calculatePrice_WhenAllIngredientsAreFound_ShouldCalculateThePrice(){

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

        Mockito.when(this.mockedProductRepository.
                        findById(productEntityTest.getId()))
                .thenReturn(Optional.ofNullable(productEntityTest));

        ProductEntity expected = this.productEntityTest;

        List<ProductEntity> actual = toTest.checkIfExistSuchProduct("Test", "Буркан1");
    }

}
