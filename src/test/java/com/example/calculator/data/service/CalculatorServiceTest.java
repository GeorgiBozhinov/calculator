package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.repository.IngredientRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @Mock
    private IngredientRepository mockIngredientRepository;

    private CalculatorService toTest;

    @BeforeEach
    void setUp() {

        toTest = new CalculatorService(
                mockIngredientRepository
        );
    }

    @Test
    void testLoadIngredientByIngredientNAme_IngredientExist() {
        //arrange
        IngredientEntity testIngEntity = new IngredientEntity();
        testIngEntity.setIngredientName("тест");
        testIngEntity.setIngredientType("тест");
        testIngEntity.setQuantity(1);
        testIngEntity.setSize(0);
        testIngEntity.setUnitName("тест");
        testIngEntity.setPrice(20.0);

        //act
        when(mockIngredientRepository.findByIngredientName(testIngEntity.getIngredientName())).thenReturn(testIngEntity);

        //assert
        //CalculatorService calculatorService = toTest.
    }

//    @Test
//    public void checkIfTheReturnVariableTypeOFCalcPriceIsDouble() {
//        //Mockito.mock(CalculatorService.class);
//
//        CalculatorDTO calculatorDTO = new CalculatorDTO();
//        // 5.42
//        calculatorDTO.setWaxType("Соев восък");
//        calculatorDTO.setJarOwner("seller");
//        calculatorDTO.setCandleJar("Бурканче 130мл");
//        calculatorDTO.setWaxQuantity(120);
//        calculatorDTO.setCandleWick("фитил ф6-7см");
//        calculatorDTO.setWickSize(6);
//        calculatorDTO.setScentQuantity(5);
//
//        ArrayList<String> arrL = new ArrayList<>();
//        arrL.add("Пръчка канела");
//
//        calculatorDTO.setAdditionalIngredients(arrL);
//        Double expectedValue = 5.0;
//
//        Assert.assertEquals(expectedValue, calculatorService.calcPrice(calculatorDTO));
//    }
//
//    //@BeforeEach
//    @Test
//    public void validateTheReturnedResultOFCalcPrice() {
//        //Mockito.mock(CalculatorService.class);
//
//        CalculatorDTO calculatorDTO = new CalculatorDTO();
//        // 5.42
//        calculatorDTO.setWaxType("Соев восък");
//        calculatorDTO.setJarOwner("seller");
//        calculatorDTO.setCandleJar("Бурканче 130мл");
//        calculatorDTO.setWaxQuantity(120);
//        calculatorDTO.setCandleWick("фитил ф6-7см");
//        calculatorDTO.setWickSize(6);
//        calculatorDTO.setScentQuantity(5);
//
//        ArrayList<String> arrL = new ArrayList<>();
//        arrL.add("Пръчка канела");
//
//        calculatorDTO.setAdditionalIngredients(arrL);
//        Double expectedValue = 5.42;
//
//       // Assert.assertEquals(expectedValue, calculatorService.calcPrice(calculatorDTO));
//    }
}
