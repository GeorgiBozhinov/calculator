package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.model.dto.CalculatorDTO;
import com.example.calculator.data.repository.IngredientRepository;
import com.example.calculator.data.repository.UnitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

    @Mock
    private IngredientRepository mockIngredientRepository;

    @Mock
    private UnitRepository mockUnitRepository;

    private CalculatorService toTest;

    @BeforeEach
    void setUp() {

        toTest = new CalculatorService(
                mockIngredientRepository
        );
    }

    @Test
    void testCalcPrice_ReturnDouble_WhenAllIngredientsAreAvailable() {

//        UnitEntity unitEntity = new UnitEntity();
//        unitEntity.setUnitName("кг");
//
//        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);
//
//        unitEntity.setUnitName("гр");
//
//        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);
//        unitEntity.setUnitName("см");
//
//        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);
//        unitEntity.setUnitName("мл");
//
//        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);

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

        CalculatorDTO calculatorDTO = new CalculatorDTO();

        calculatorDTO.setJarOwner("seller");
        calculatorDTO.setWickSize(5);
        calculatorDTO.setScentType("Шоколад");
        calculatorDTO.setWaxQuantity(110);
        calculatorDTO.setCandleJar("Бурканче 130 мл");
        calculatorDTO.setWaxType("Соев восък");
        calculatorDTO.setCandleWick("фитил ф4-5");
        calculatorDTO.setScentQuantity(5);
        calculatorDTO.setAdditionalIngredients(List.of("Пръчка-1"));

        when(mockIngredientRepository.findByIngredientName(calculatorDTO.getWaxType())).thenReturn(entityWax);
        when(mockIngredientRepository.findByIngredientName(calculatorDTO.getCandleJar())).thenReturn(entityJar);
        when(mockIngredientRepository.findByIngredientName(calculatorDTO.getCandleWick())).thenReturn(entityWick);
        when(mockIngredientRepository.findByIngredientName(calculatorDTO.getScentType())).thenReturn(entityScent);
        when(mockIngredientRepository.findByIngredientName("Пръчка")).thenReturn(entityCinnamonOthers);

        Double actualPrice = toTest.calcPrice(calculatorDTO);
       // System.out.println("actual price is: " + actualPrice);

        //assert
        Assertions.assertEquals(26.61, actualPrice);
    }

}
