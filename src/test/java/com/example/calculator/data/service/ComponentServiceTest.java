package com.example.calculator.data.service;
import com.example.calculator.data.base_entities.ComponentEntity;
import com.example.calculator.data.repository.ComponentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testGetAllComponent_ComponentsExist() {

        ComponentEntity componentEntity = new ComponentEntity();
        componentEntity.setComponentName("test");
        componentEntity.setComponentNameEn("testEn");

        List<ComponentEntity> expectedList = new ArrayList<>();
        expectedList.add(componentEntity);

        when(mockComponentRepository.findAll()).thenReturn(expectedList);

        //act
        List<ComponentEntity> listOfComponents = toTest.getAllComponents();

        System.out.println("Actual: " + listOfComponents.get(0).toString());
        System.out.println("Expected: " + expectedList.get(0).toString());

        //assert
        Assertions.assertEquals(expectedList, listOfComponents);

    }

    @Test
    void testGetAllComponent_ComponentsDoesNotExist() {

        ComponentEntity componentEntity = new ComponentEntity();
        componentEntity.setComponentName("test");
        componentEntity.setComponentNameEn("testEn");

        List<ComponentEntity> expectedList = new ArrayList<>();
        //expectedList.add(componentEntity);

        when(mockComponentRepository.findAll()).thenReturn(expectedList);

        //act
        List<ComponentEntity> listOfComponents = toTest.getAllComponents();

        //assert
        Assertions.assertEquals(expectedList, listOfComponents);

    }

}
