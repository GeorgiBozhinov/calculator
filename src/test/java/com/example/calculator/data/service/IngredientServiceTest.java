//package com.example.calculator.data.service;
//import com.example.calculator.data.repository.IngredientRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class IngredientServiceTest {
//
//    @Mock
//    private IngredientRepository ingredientRepository;
//
//    @MockBean
//    private IngredientService ingredientService;
//
//    private ModelMapper modelMapper;
//
//    @BeforeEach
//    void setUp() {
//
//        this.ingredientService
//                = new IngredientService(this.ingredientRepository, this.modelMapper);
//    }
//
//    @Test
//    public void getAllIngredients() {
//
//        ingredientService.getAllIngredients();
//        verify(ingredientRepository).findAll();
//    }
//
//}
