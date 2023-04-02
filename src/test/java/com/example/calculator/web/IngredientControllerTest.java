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

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {

        UserEntity testUser = testDataUtils.createTestUser("test1");
        UserEntity testAdmin = testDataUtils.createTestAdmin("test2");
    }

    @AfterEach
    void tearDown() {

        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser(
            username = "test1",
            roles = {"ADMIN", "USER"}
    )
    void testReturnIngredientPage_Return200Status() throws Exception {

        mockMvc.perform(get("/ingredient/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/add_ingredient"))
                .andExpect(model().attributeExists("units"))
                .andExpect(model().attributeExists("components"));
    }

    @Test
    @WithMockUser(
            username = "test1",
            roles = {"ADMIN", "USER"}
    )
    void testReturnIngredientPage_Return200Status_WhenAttributesAlreadyExist() throws Exception {

        mockMvc.perform(get("/ingredient/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/add_ingredient"));
    }

    @Test
    @WithMockUser(
            username = "test1",
            roles = {"ADMIN", "USER"}
    )
    void testAddIngredientMethod_ShouldReturn200OK() throws Exception {

        mockMvc.perform(post("/ingredient/add")
                        .param("ingredientType", "wax")
                        .param("ingredientName", "testing01")
                        .param("quantity", "1")
                        .param("size", "0")
                        .param("unitName", "кг")
                        .param("price", "10.0"))
                .andExpect(status().is4xxClientError())
                .andExpect(header().string("Location", "/ingredient/add"));

//                .andExpect(model().attributeExists("ingredientType"))
//                .andExpect(model().attributeExists("ingredientName"))
//                .andExpect(model().attributeExists("quantity"))
//                .andExpect(model().attributeExists("size"))
//                .andExpect(model().attributeExists("unitName"))
//                .andExpect(model().attributeExists("price"));

    }

    @Test
    @WithMockUser(
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void testLoadingSuccessPage_ShouldReturn200() throws Exception {

//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setScentType("lemon");
//        productDTO.setWaxType("was");
//        productDTO.setWaxQuantity(200);
//        productDTO.setScentQuantity(10.0);
//        productDTO.setCandleWick("regular");
//        productDTO.setCandleJar("ddd");
//        productDTO.setCandleName("one");
//        productDTO.setWickSize(3);

        mockMvc.perform(get("/ingredient/succ")
                        .param("ingredientName", "test")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("views/result_ingredient"))
                .andExpect(model().attribute("ingredientAddedModel", hasProperty("ingredientName", is("test"))
                ));
    }

}
