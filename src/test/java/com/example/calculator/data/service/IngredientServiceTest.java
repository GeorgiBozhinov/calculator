package com.example.calculator.data.service;

import com.example.calculator.data.base_entities.IngredientEntity;
import com.example.calculator.data.model.dto.IngredientDTO;
import com.example.calculator.data.repository.IngredientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

    private IngredientEntity ingredientEntityTest;

    @Mock
    private IngredientRepository mockedIngredientRepository;

    private IngredientService toTest;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        modelMapper = new ModelMapper();
        toTest = new IngredientService(mockedIngredientRepository, modelMapper);
    }


   /*   TO TEST
    addIngredient() - tested
    updateIngredient() - tested
    deleteIngredientById() - tested
    getAllIngredients() - tested
    findByIngredientType() - tested
    findIngredient() - tested
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
                findByIngredientName("Test")).thenReturn(ingredientEntityTest);

        IngredientEntity actual = toTest.checkIfExistSuchIngredient("Test");

        Assertions.assertEquals(ingredientEntityTest.getIngredientName(), actual.getIngredientName());
    }

    @Test
    public void updateIngredient_WhenExist_ShouldUpdateIt() {

        long id = 1;

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(23.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(id);
        }};

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(id);
//        ingredientDTO.setIngredientType("wax");
        ingredientDTO.setIngredientName("Восък");
        ingredientDTO.setPrice(24.0);
        ingredientDTO.setQuantity(1);
        ingredientDTO.setSize(0);
        ingredientDTO.setUnitName("кг");

        when(this.mockedIngredientRepository.findByIdCustom(1L)).thenReturn(ingredientEntityTest);

        IngredientService ingredientService = new IngredientService(this.mockedIngredientRepository, modelMapper);

        ingredientService.updateIngredient(ingredientDTO, id);

        IngredientEntity expectedEntity = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(24.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(id);
        }};

        when(this.mockedIngredientRepository.findByIdCustom(id)).thenReturn(expectedEntity);
        IngredientEntity actual = ingredientService.findIngredientById(id);
        //System.out.println(ingEntity.getPrice());
        Assertions.assertEquals(expectedEntity.getId(), actual.getId());
        Assertions.assertEquals(expectedEntity.getIngredientName(), actual.getIngredientName());
        Assertions.assertEquals(expectedEntity.getIngredientType(), actual.getIngredientType());
        Assertions.assertEquals(expectedEntity.getSize(), actual.getSize());
        Assertions.assertEquals(expectedEntity.getUnitName(), actual.getUnitName());
        Assertions.assertEquals(expectedEntity.getQuantity(), actual.getQuantity());
        Assertions.assertEquals(expectedEntity.getPrice(), actual.getPrice());
    }

    @Test
    public void deleteIngredient_WhenExist() {
        long id = 1;

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(24.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(id);
        }};

        toTest.deleteIngredientById(id);
        verify(mockedIngredientRepository, times(1)).deleteById(id);

    }

    @Test
    public void getAllIngredients_ShouldReturnResult() {

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
                findAllByOrderByIngredientType())
                .thenReturn(listOfIngredients);

        IngredientService ingredientService = new IngredientService(this.mockedIngredientRepository, modelMapper);

        List actual = ingredientService.getAllIngredients();

        Assertions.assertEquals(listOfIngredients.size(), actual.size());
    }

    @Test
    public void updateIngredient_ChangeSomeParameter_ShouldUpdateItIfExist() {

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Test");
            setIngredientType("wax");
            setSize(0);
            setPrice(11.0);
            setUnitName("кг");
            setQuantity(1);
            setId(1L);
        }};

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredientName("Test");
        ingredientDTO.setIngredientType("wax");
        ingredientDTO.setSize(0);
        ingredientDTO.setPrice(12.0);
        ingredientDTO.setUnitName("кг");
        ingredientDTO.setQuantity(1);
        ingredientDTO.setId(1L);

        when(this.mockedIngredientRepository.findByIdCustom(1L)).thenReturn(ingredientEntityTest);

        IngredientEntity ingredientEntity = modelMapper.map(ingredientDTO, IngredientEntity.class);
        when(this.mockedIngredientRepository.saveAndFlush(ingredientEntity)).thenReturn(ingredientEntity);

        toTest.updateIngredient(ingredientDTO, 1L);
        IngredientEntity actual = toTest.findIngredientById(1L);

        Assertions.assertEquals(ingredientDTO.getIngredientName(), actual.getIngredientName());
        Assertions.assertEquals(ingredientDTO.getIngredientType(), actual.getIngredientType());
        Assertions.assertEquals(ingredientDTO.getSize(), actual.getSize());
        Assertions.assertEquals(ingredientDTO.getPrice(), actual.getPrice());
        Assertions.assertEquals(ingredientDTO.getUnitName(), actual.getUnitName());
        Assertions.assertEquals(ingredientDTO.getQuantity(), actual.getQuantity());
        Assertions.assertEquals(ingredientDTO.getId(), actual.getId());
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

    @Test
    public void testFindIngredientByID_ShouldReturnIt() {

        this.ingredientEntityTest = new IngredientEntity() {{
            setIngredientName("Восък");
            setIngredientType("wax");
            setPrice(23.0);
            setQuantity(1);
            setSize(0);
            setUnitName("кг");
            setId(1L);
        }};

        when(this.mockedIngredientRepository.
                findByIdCustom(1L))
                .thenReturn(this.ingredientEntityTest);

        IngredientEntity actualIngredient = toTest.findIngredientById(1L);

        Assertions.assertEquals(ingredientEntityTest.getIngredientName(), actualIngredient.getIngredientName());
        Assertions.assertEquals(ingredientEntityTest.getIngredientType(), actualIngredient.getIngredientType());
        Assertions.assertEquals(ingredientEntityTest.getPrice(), actualIngredient.getPrice());
        Assertions.assertEquals(ingredientEntityTest.getQuantity(), actualIngredient.getQuantity());
        Assertions.assertEquals(ingredientEntityTest.getSize(), actualIngredient.getSize());
        Assertions.assertEquals(ingredientEntityTest.getUnitName(), actualIngredient.getUnitName());
        Assertions.assertEquals(ingredientEntityTest.getId(), actualIngredient.getId());
    }

}



