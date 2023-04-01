package com.example.calculator.data.repository;

import com.example.calculator.data.base_entities.IngredientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class IngredientRepositoryTest {
    private static final String INGREDIENT_TYPE_WAX = "wax";
    private static final String INGREDIENT_TYPE_JAR = "jar";

    @Autowired
    private IngredientRepository ingredientRepository;


    @Test
    void TestFindAllByIngredientType_ShouldReturnResult(){
        IngredientEntity ingredientEntity = getIngredient();
        ingredientRepository.save(ingredientEntity);
        List<String> ingredientNames = ingredientRepository.findAllByIngredientType(INGREDIENT_TYPE_JAR);

        for ( String ingredientName: ingredientNames) {
            System.out.println(ingredientName);
            //expected, actual
            assertEquals("Бурканче 130 мл", ingredientName);
        }

    }

    @Test
    void TestFindByIngredientNameAndIngredientType_ShouldReturnThePrice(){
        Double actualPrice = ingredientRepository.findByIngredientNameAndIngredientType("Бурканче 130 мл", INGREDIENT_TYPE_JAR);
        Double expectedPrice = 0.35;
        assertEquals(expectedPrice, actualPrice);
    }


    @Test
    void TestFindByIngredientName(){
        IngredientEntity ingredientEntity = getIngredient();
        IngredientEntity entityActual = ingredientRepository.findByIngredientName("Бурканче 130 мл");

        assertEquals(ingredientEntity.getIngredientName(), entityActual.getIngredientName());
        assertEquals(ingredientEntity.getIngredientType(), entityActual.getIngredientType());
        assertEquals(ingredientEntity.getQuantity(), entityActual.getQuantity());
        assertEquals(ingredientEntity.getPrice(), entityActual.getPrice());
        assertEquals(ingredientEntity.getUnitName(), entityActual.getUnitName());
    }


    private IngredientEntity getIngredient(){
        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setIngredientType("jar");
        ingredientEntity.setIngredientName("Бурканче 130 мл");
        ingredientEntity.setQuantity(130);
        ingredientEntity.setSize(0);
        ingredientEntity.setUnitName("мл");
        ingredientEntity.setPrice(0.35);

        return ingredientEntity;
    }

}
