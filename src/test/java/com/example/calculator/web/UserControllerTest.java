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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {


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
    void  testLoginPageShown() throws Exception {
        mockMvc.perform(get("/users/login")).
                andExpect(status().isOk()).
                andExpect(view().name("auth/login"));
    }


    @Test
    @WithMockUser(
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void  testPageShowingAllUsers_ShouldReturn200() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/manage_users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void  testEditPageForUsers_ShouldReturn200() throws Exception {
        mockMvc.perform(get("/users/edit")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("views/update_user"))
                .andExpect(model().attributeExists("userModel"));
    }


    @Test
    @WithMockUser(
            username = "test2",
            roles = {"ADMIN", "USER"}
    )
    void  testDeleteUser_ShouldDeleteIt() throws Exception {
        mockMvc.perform(get("/users/delete")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/users/all"));
    }

}
