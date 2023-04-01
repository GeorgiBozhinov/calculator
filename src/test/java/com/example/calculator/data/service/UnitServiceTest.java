package com.example.calculator.data.service;

import com.example.calculator.data.base_entities.UnitEntity;
import com.example.calculator.data.repository.UnitRepository;
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
public class UnitServiceTest {

    @Mock
    private UnitRepository mockUnitRepository;

    private UnitService toTest;

    @BeforeEach
    void setUp() {
        toTest = new UnitService(
                mockUnitRepository
        );
    }

    @Test
    void testGetAllUnits_UnitsExist(){

        UnitEntity unitEntity = new UnitEntity();
        unitEntity.setUnitName("кг");

        List<UnitEntity> listOFUnits = new ArrayList<>();
        listOFUnits.add(unitEntity);

        when(mockUnitRepository.findAll()).thenReturn(listOFUnits);

        //act
        List<UnitEntity> listUnits = toTest.getAllUnits();

        System.out.println("expected size: " + listOFUnits.size());
        System.out.println("actual size: " + listUnits.size());
        //assert
        Assertions.assertEquals(listOFUnits, listUnits);

    }

    @Test
    void testGetAllUnits_UnitsDoesNotExist(){

        List<UnitEntity> expectedListOFUnits = new ArrayList<>();

        when(mockUnitRepository.findAll()).thenReturn(expectedListOFUnits);

        //act
        List<UnitEntity> actualListOfUnits = toTest.getAllUnits();

        System.out.println("expected size: " + expectedListOFUnits.size());
        System.out.println("actual size: " + actualListOfUnits.size());
        //assert
        Assertions.assertEquals(expectedListOFUnits, actualListOfUnits);
    }


}
