package com.example.calculator.data.service;


import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.repository.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    private IngredientEntity ingredientEntityTest;

    @Mock
    private IngredientRepository mockedIngredientRepository;

    private IngredientService toTest;

    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        toTest = new IngredientService(mockedIngredientRepository, modelMapper);
    }


   /*   TO TEST
    addIngredient()
    updateIngredient()
    deleteIngredientById()
    getAllIngredients() - tested
    findByIngredientType() - tested
    findIngredient()
    findIngredientById() - tested
    checkIfExistSuchIngredient() - tested
    */

    @Test
    public void addIngredient_AddIngredientWhenAllValidationAreOk_ShouldAddIt() {

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Test");
            setIngredientType("wax");
            setSize(0);
            setPrice(11.0);
            setUnitName("кг");
            setQuantity(1);
        }};

        when(this.mockedIngredientRepository.
                saveAndFlush(ingredientEntityTest)).thenReturn(ingredientEntityTest);

        //when
        //IngredientService ingredientService = new IngredientService(this.mockedIngredientRepository, modelMapper);

        IngredientEntity actual = toTest.checkIfExistSuchIngredient("Test");

        Assertions.assertEquals(ingredientEntityTest.getIngredientName(), actual.getIngredientName());


    }


    @Test
    public void checkFindByIngredientType_GetIngredientListWhenExist_ShouldReturnRecord() {

        List<IngredientEntity> listOfIngredients = new ArrayList<>();

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(23.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(1);
        }};

        listOfIngredients.add(ingredientEntityTest);

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък 2");
            setIngredientType("wax");
            setPrice(24.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(2);
        }};

        listOfIngredients.add(ingredientEntityTest);


        when(this.mockedIngredientRepository.
                        findAllByIngredientType("wax"))
                .thenReturn(listOfIngredients);

        IngredientService ingredientService = new IngredientService(this.mockedIngredientRepository, modelMapper);

        List actual = ingredientService.findByIngredientType("wax");

        Assertions.assertEquals(2, listOfIngredients.size());
        Assertions.assertEquals(2, actual.size());
    }

    @Test
    public void checkFindByIngredientById_GetIngredientByIdWhenExist_ShouldReturnRecord() {

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(23.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(1);
        }};

        when(this.mockedIngredientRepository.
                        findByIngredientName("Восък"))
                .thenReturn(this.ingredientEntityTest);

        IngredientService ingredientService = new IngredientService(this.mockedIngredientRepository, modelMapper);

        IngredientEntity expected = this.ingredientEntityTest;

        IngredientEntity actual = ingredientService.checkIfExistSuchIngredient("Восък");

        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getIngredientName(), actual.getIngredientName());
        Assertions.assertEquals(expected.getIngredientType(), actual.getIngredientType());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assertions.assertEquals(expected.getUnitName(), actual.getUnitName());
        Assertions.assertEquals(expected.getSize(), actual.getSize());
    }

    @Test
    public void checkIfExistSuchIngredient_GetIngredientWhenExist_ShouldReturnCorrect() {

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(23.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
        }};

        when(this.mockedIngredientRepository.
                        findByIngredientName("Восък"))
                .thenReturn(this.ingredientEntityTest);

        IngredientService ingredientService = new IngredientService(this.mockedIngredientRepository, modelMapper);

        IngredientEntity expected = this.ingredientEntityTest;

        IngredientEntity actual = ingredientService.checkIfExistSuchIngredient("Восък");

        Assertions.assertEquals(expected.getIngredientName(), actual.getIngredientName());
        Assertions.assertEquals(expected.getIngredientType(), actual.getIngredientType());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getQuantity(), actual.getQuantity());
        Assertions.assertEquals(expected.getUnitName(), actual.getUnitName());
        Assertions.assertEquals(expected.getSize(), actual.getSize());
    }

}



