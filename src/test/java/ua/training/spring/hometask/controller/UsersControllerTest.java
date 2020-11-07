package ua.training.spring.hometask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.User;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"TEST", "MOCK_BEANS", "IN_MEMORY"})
@WebAppConfiguration
class UsersControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("users"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void addUser() throws Exception {
        mockMvc.perform(post("/users/add")
                .param("birthday", String.valueOf(LocalDate.now()))
                .flashAttr("user", new User()))
                .andExpect(redirectedUrl("/users"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void remove() throws Exception {
        mockMvc.perform(get("/users/remove/{id}", 1L))
                .andExpect(redirectedUrl("/users"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/users/getbyid/{id}", 1000L))
                .andExpect(view().name("users"))
                .andExpect(status().isOk());
    }

    @Test
    void getByEmail() throws Exception {
        mockMvc.perform(get("/users/getbyemail/")
                .param("email", "testUserEmail"))
                .andExpect(view().name("users"))
                .andExpect(status().isOk());
    }
}