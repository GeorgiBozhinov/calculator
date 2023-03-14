package com.example.calculator.web;
import com.example.calculator.data.service.CalculatorService;
import com.example.calculator.data.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user@example.com");
        testAdmin = testDataUtils.createTestAdmin("admin@example.com");
        var testModel =
                testDataUtils.createTestModel(testDataUtils.createTestBrand());

        testOffer = testDataUtils.createTestOffer(testUser, testModel);
        testAdminOffer = testDataUtils.createTestOffer(testAdmin, testModel);
    }


    @WithMockUser(value = "spring")
    @Test
    void  testCalculatorPageShown() throws Exception {
        mockMvc.perform(get("/private/calc")).
                andExpect(status().isOk()).
                andExpect(view().name("views/calculator")).
                andExpect(model().attributeExists("waxes")).
                andExpect(model().attributeExists("jars")).
                andExpect(model().attributeExists("scents")).
                andExpect(model().attributeExists("wicks")).
                andExpect(model().attributeExists("others"));
    }

}
