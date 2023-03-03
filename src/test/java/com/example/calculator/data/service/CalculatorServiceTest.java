package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.base_entities.UnitEntity;
import com.example.calculator.data.model.dto.CalculatorDTO;
import com.example.calculator.data.repository.ComponentRepository;
import com.example.calculator.data.repository.IngredientRepository;
import com.example.calculator.data.repository.UnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
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
    void testCalcPrice_ReturnDouble_WhenAllIngredientsAreAvailable(){

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setUnitName("кг");

        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);

        unitEntity.setUnitName("гр");

        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);
        unitEntity.setUnitName("см");

        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);
        unitEntity.setUnitName("мл");

        when(mockUnitRepository.save(any(UnitEntity.class))).thenReturn(unitEntity);

        IngredientEntity ingredientEntity = new IngredientEntity();

        ingredientEntity.setIngredientName("jar1");
        ingredientEntity.setPrice(10.0);
        ingredientEntity.setSize(0);
        ingredientEntity.setQuantity(200);
        ingredientEntity.setUnitName("гр");
        ingredientEntity.setIngredientType("jar");


        when(mockIngredientRepository.save(any(IngredientEntity.class))).thenReturn(ingredientEntity);

        ingredientEntity.setIngredientName("aromat1");
        ingredientEntity.setPrice(5.0);
        ingredientEntity.setSize(0);
        ingredientEntity.setQuantity(10);
        ingredientEntity.setUnitName("мл");
        ingredientEntity.setIngredientType("scent");

        when(mockIngredientRepository.save(any(IngredientEntity.class))).thenReturn(ingredientEntity);

        ingredientEntity.setIngredientName("wick1");
        ingredientEntity.setPrice(6.0);
        ingredientEntity.setSize(10);
        ingredientEntity.setQuantity(0);
        ingredientEntity.setUnitName("см");
        ingredientEntity.setIngredientType("wick");

        when(mockIngredientRepository.save(any(IngredientEntity.class))).thenReturn(ingredientEntity);

        ingredientEntity.setIngredientName("wax-normal");
        ingredientEntity.setPrice(10.0);
        ingredientEntity.setSize(0);
        ingredientEntity.setQuantity(1000);
        ingredientEntity.setUnitName("гр");
        ingredientEntity.setIngredientType("wax");

        when(mockIngredientRepository.save(any(IngredientEntity.class))).thenReturn(ingredientEntity);

        CalculatorDTO calculatorDTO = new CalculatorDTO();

        calculatorDTO.setJarOwner("seller");
        calculatorDTO.setWickSize(5);
        calculatorDTO.setScentType("aromat1");
        calculatorDTO.setWaxQuantity(200);
        calculatorDTO.setCandleJar("jar1");
        calculatorDTO.setWaxType("wax-normal");
        calculatorDTO.setCandleWick("wick1");
        calculatorDTO.setScentQuantity(5);

//        List<UnitEntity> listOfUnitEntities = mockUnitRepository.findAll();
//        List<IngredientEntity> listOfIngredientEntities = mockIngredientRepository.findAll();

        double actual = toTest.calcPrice(calculatorDTO);

        String test = "test";

//        CalculatorDTO calculatorDTO = new CalculatorDTO();
//
//        calculatorDTO.setCandleJar("jar1");
//        calculatorDTO.setCandleWick("wick1");
//        calculatorDTO.setScentType("type1");
//        calculatorDTO.setWaxQuantity(100);
//        calculatorDTO.setWickSize(5);
//        calculatorDTO.setScentQuantity(5);

    }

}
