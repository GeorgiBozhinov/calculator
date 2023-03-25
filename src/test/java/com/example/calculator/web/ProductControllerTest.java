package com.example.calculator.web;
import com.example.calculator.data.base_entities.UserEntity;
import com.example.calculator.data.service.IngredientService;
import com.example.calculator.data.service.ProductService;
import com.example.calculator.data.service.imagesFolder.ImageService;
import com.example.calculator.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ImageService imageService;

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

    @Test
    @WithMockUser(
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void testGettingSuccessPage() throws Exception {

        mockMvc.perform(get("/product/succ")).
                andExpect(status().isOk()).
                andExpect(view().name("views/result_product"));
    }

    @Test
    @WithMockUser(
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void testAddProductMethod() throws Exception {

        mockMvc.perform(post("/product/add")
                .param("candleName", "test")
                .param("waxType", "test2")
                .param("candleJar", "test2")
                .param("waxQuantity", "100")
                .param("candleWick", "normal")
                .param("wickSize", "10")
                .param("scentType", "lemon")
                .param("scentQuantity", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("views/result_product"));
    }

}

