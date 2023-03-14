package com.example.calculator.web;
import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser, testAdmin;

    @BeforeEach
    void setUp() {

        testUser = testDataUtils.createTestUser("test1");
        testAdmin = testDataUtils.createTestAdmin("test2");
    }

    @AfterEach
    void tearDown() {

        testDataUtils.cleanUpDatabase();
    }


    @Test
    @WithMockUser(
            username = "spring",
            roles = {"ADMIN", "USER"}
    )
    void testCalculatorPageShown_WhenUserIsPassed() throws Exception {

        mockMvc.perform(get("/calc")).
                andExpect(status().isOk()).
                andExpect(view().name("views/calculator")).
                andExpect(model().attributeExists("waxes")).
                andExpect(model().attributeExists("jars")).
                andExpect(model().attributeExists("scents")).
                andExpect(model().attributeExists("wicks")).
                andExpect(model().attributeExists("others"));
    }

    @Test
    void testCalculatorPageShown_WhenUserIsNotPassed_302Expected() throws Exception {

        mockMvc.perform(get("/calc")).
                andExpect(status().isOk()).
                andExpect(view().name("views/calculator")).
                andExpect(model().attributeExists("waxes")).
                andExpect(model().attributeExists("jars")).
                andExpect(model().attributeExists("scents")).
                andExpect(model().attributeExists("wicks")).
                andExpect(model().attributeExists("others"));
    }

}
