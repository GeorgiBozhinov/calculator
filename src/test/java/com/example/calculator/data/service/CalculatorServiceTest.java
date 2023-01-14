//package com.example.calculator.data.service;
//
//import com.example.calculator.data.model.dto.CalculatorDTO;
//import com.example.calculator.data.repository.IngredientRepository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import java.util.ArrayList;
//
//@ExtendWith(MockitoExtension.class)
//public class CalculatorServiceTest {
//
//    @Mock
//    IngredientRepository ingredientRepository;
//
//
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
//
//}
