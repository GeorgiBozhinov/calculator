package com.example.calculator.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistrationPageShown() throws Exception {

        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("auth/register"));
    }


    @Test
    void testGetRegisterPage() throws Exception {

        mockMvc.perform(get("/users/register")).
                andExpect(status().isOk()).
                andExpect(view().name("auth/register"));

    }

    @Test
    void testUserRegistration() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("username", "pesho")
                        .param("firstName", "Petre")
                        .param("lastName", "Petrov")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    //Testing when 1 parameter is missing
    @Test
    void testUserRegistration_ShouldRedirect_MissingParameter() throws Exception {

        mockMvc.perform(post("/users/register")
                       // .param("username", "pesho")
                        .param("firstName", "Petre")
                        .param("lastName", "Petrov")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    //Testing when passwords are NOT equal
    @Test
    void testUserRegistration_ShouldRedirect_PasswordsAreDifferent() throws Exception {

        mockMvc.perform(post("/users/register")
                        .param("username", "pesho")
                        .param("firstName", "Petre")
                        .param("lastName", "Petrov")
                        .param("password", "test123")
                        .param("confirmPassword", "TEST123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));
    }

    //Need to test when user already exist
//    @Test
//    void testUserRegistration_ShouldRedirect_UserAlreadyExist() throws Exception {
//
//        mockMvc.perform(post("/users/register")
//                        .param("username", "pesho")
//                        .param("firstName", "Petre")
//                        .param("lastName", "Petrov")
//                        .param("password", "test123")
//                        .param("confirmPassword", "TEST123")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/users/register"));
//    }
}




