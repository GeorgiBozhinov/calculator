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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest

public class ProductControllerTest {

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
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void testProductAddPageShown_WhenUserIsPassed() throws Exception {

        mockMvc.perform(get("/product/add")).
                andExpect(status().isOk()).
                andExpect(view().name("views/add_product"))
                .andExpect(model().attributeExists("waxes"))
                .andExpect(model().attributeExists("jars"))
                .andExpect(model().attributeExists("scents"))
                .andExpect(model().attributeExists("wicks"))
                .andExpect(model().attributeExists("others"))
                .andExpect(model().attributeExists("options"));
    }

    @Test
    void testProductAddPageShown_Forbidden() throws Exception {

        mockMvc.perform(get("/product/add")).
                andExpect(status().isOk()).
                andExpect(view().name("views/add_product"))
                .andExpect(model().attributeExists("waxes"))
                .andExpect(model().attributeExists("jars"))
                .andExpect(model().attributeExists("scents"))
                .andExpect(model().attributeExists("wicks"))
                .andExpect(model().attributeExists("others"))
                .andExpect(model().attributeExists("options"));
    }

//    @Test
//    void testAddingProduct_whenValidInput_return200() {
//
//        ProductDTO productDTO = new ProductDTO();
//
//        productDTO.setCandleName("Test");
//        productDTO.setCandleJar("jar2");
//        productDTO.setCandleWick("normal");
//        productDTO.setScentQuantity(2.0);
//        productDTO.setWaxQuantity(200);
//        productDTO.setWaxType("wax");
//        productDTO.setScentType("lemon");
//        productDTO.setAdditionalIngredients(List.of("one-1", "two-2"));
//
//        mockMvc.perform(post("/product/add")
//                        .contentType("application/json")
//                        .content(productDTO))
//                .andExpect(status().isOk())
//                .andExpect(view().name("views/add_product"));
//    }


    @Test
    void testGettingSuccessPage() throws Exception {
        mockMvc.perform(get("/product/succ")).
                andExpect(status().isOk()).
                andExpect(view().name("views/result_product"));


    }

}

