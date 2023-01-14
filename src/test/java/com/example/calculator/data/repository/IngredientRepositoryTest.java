package com.example.calculator.data.repository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    public void checkIfTheReturnedValueIsDouble(){
        String ingredientName = "Соев восък";
        String ingredientType = "wax";

        Double expectedValue = 23.0;

        Assert.assertEquals(expectedValue, ingredientRepository.findByIngredientNameAndIngredientType(ingredientName, ingredientType));
    }

}
