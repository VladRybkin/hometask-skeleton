package ua.training.spring.hometask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.training.spring.hometask.config.BeansConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.domain.Event;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeansConfiguration.class, WebMvcConfig.class})
@ActiveProfiles({"TEST", "MOCK_BEANS", "IN_MEMORY"})
@WebAppConfiguration
class EventsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getEvents() throws Exception {
        mockMvc.perform(get("/events"))
                .andExpect(model().attributeExists("events"))
                .andExpect(view().name("events"))
                .andExpect(status().isOk());
    }

    @Test
    void addEvent() throws Exception {
        Event event = new Event();
        mockMvc.perform(post("/events/add")
                .param("eventDate", LocalDateTime.now().truncatedTo(MINUTES).toString())
                .param("auditoriumName", "test auditorium")
                .flashAttr("event", event))
                .andExpect(redirectedUrl("/events"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(get("/events/remove/{id}", 1L))
                .andExpect(redirectedUrl("/events"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/events/getbyid/{id}", 1000L))
                .andExpect(view().name("events"))
                .andExpect(status().isOk());
    }

    @Test
    void getByName() throws Exception {
        mockMvc.perform(get("/events/getbyname/")
                .param("name", "testEventName"))
                .andExpect(view().name("events"))
                .andExpect(status().isOk());
    }
}