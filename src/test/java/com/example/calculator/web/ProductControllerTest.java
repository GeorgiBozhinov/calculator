package com.example.calculator.web;
import com.example.calculator.config.SecurityConfiguration;
import com.example.calculator.data.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityConfiguration securityConfiguration;

    @Test
    void testProductAddPageShown() throws Exception {

        mockMvc.perform(get("/product/add")).
                andExpect(status().isOk()).
                andExpect(view().name("views/add_product"))
                .andExpect(model().attributeExists("wax"))
                .andExpect(model().attributeExists("jar"))
                .andExpect(model().attributeExists("scent"))
                .andExpect(model().attributeExists("wick"))
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

