//package com.example.calculator.web.rest;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
////@ExtendWith(SpringExtension.class)
////@WebMvcTest(value = RestControllerCheck.class)
//@RunWith(SpringRunner.class)
//@WebMvcTest(RestControllerCheck.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class RestControllerCheckTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private RestControllerCheck controller;
//
//    @BeforeEach
//    void setUp() {
//
//        mockMvc = MockMvcBuilders.
//                standaloneSetup(controller).
//                build();
//    }
//
//    @Test
//    public void getIngredient_whenJarIsEmpty_thenReturnMessage() throws Exception {
//
//        String jarType = "";
//        int waxQuantity = 10;
//        String uri = "/check";
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).
//                param("jarType", jarType).
//                param("waxQuantity", String.valueOf(waxQuantity));
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse mockHttpServletResponse = result.getResponse();
//        String content = mockHttpServletResponse.getContentAsString();
//        System.out.println("The response is: " + result.getResponse());
//    }
//
//}
