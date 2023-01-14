package com.example.calculator.web.rest;
import com.example.calculator.configs.StringConstants;
import com.example.calculator.data.service.IngredientService;
import com.example.calculator.data.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class RestControllerCheckTest {

    @Mock
    private ProductService productService;

    @Mock
    private IngredientService ingredientService;

    @Test
    public void testGetIngredient_firstParamIsEmpty_shouldReturnNoJarMessage(){
        RestControllerCheck restControllerCheck = new RestControllerCheck(productService, ingredientService);
        String jarType = "";
        int quantity = 20;

        ResponseEntity responseEntity = restControllerCheck.getIngredient(jarType, quantity);
        String responseMessage = (String) responseEntity.getBody();
        String expectedMessage = StringConstants.NO_JAR;

        Assert.assertEquals(expectedMessage, responseMessage);

    }


    //TODO
    // not working, respository is called
    @Test
    public void testGetIngredient_whenWaXQuantityIsSmallerThanOne_shouldReturnMessage(){
        RestControllerCheck restControllerCheck = new RestControllerCheck(productService, ingredientService);
        String jarType = "тест";
        int quantity = 0;

        ResponseEntity responseEntity = restControllerCheck.getIngredient(jarType, quantity);
        String responseMessage = (String) responseEntity.getBody();
        String expectedMessage = StringConstants.NO_WAX;

        Assert.assertEquals(expectedMessage, responseMessage);
    }

}
