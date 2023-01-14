package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.ComponentEntity;
import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.repository.ComponentRepository;
import com.example.calculator.data.repository.IngredientRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComponentServiceTest {

    @Mock
    private ComponentRepository mockComponentRepository;

    private ComponentService toTest;

    @BeforeEach
    void setUp() {

        toTest = new ComponentService(
                mockComponentRepository
        );
    }

    @Test
    public void testLoadIngredientByIngredientNAme_IngredientExist() {
        //arrange
        ComponentEntity componentEntity = new ComponentEntity();
        componentEntity.setComponentName("тест");
        componentEntity.setComponentNameEn("тест");

        ComponentEntity componentEntity1= new ComponentEntity();
        componentEntity1.setComponentName("тест");
        componentEntity1.setComponentNameEn("тест");

        List<ComponentEntity> listOfComponents = new ArrayList<>();

        listOfComponents.add(componentEntity);
        listOfComponents.add(componentEntity1);

        when(mockComponentRepository.findAll()).thenReturn(listOfComponents);

        //act
        ComponentService componentService = (ComponentService) toTest.getAllComponents();

        //assert
        Assertions.assertEquals(listOfComponents, componentService);
    }

}
