package com.example.calculator.web;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    MockHttpServletRequest request;

    @Test
    void testIndexPageShown() throws Exception {

        mockMvc.perform(get("/")).
                andExpect(status().isOk()).
                andExpect(view().name("home/index"));
    }

    @Test
    void testHomePageShown() throws Exception {

        mockMvc.perform(get("/home")).
                andExpect(status().isOk()).
                andExpect(view().name("home/home")).
                andExpect(model().attributeExists("products"));
    }

}
