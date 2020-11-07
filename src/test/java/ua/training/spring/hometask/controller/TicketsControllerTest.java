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
import ua.training.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MINUTES;
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
class TicketsControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getTickets() throws Exception {
        mockMvc.perform(get("/tickets"))
                .andExpect(model().attributeExists("tickets"))
                .andExpect(view().name("tickets"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void addTicket() throws Exception {
        Ticket ticket = new Ticket();
        mockMvc.perform(post("/tickets/add")
                .param("eventName", "test event")
                .flashAttr("ticket", ticket))
                .andExpect(redirectedUrl("/tickets"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void remove() throws Exception {
        mockMvc.perform(get("/tickets/remove/{id}", 1L))
                .andExpect(redirectedUrl("/tickets"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(get("/tickets/getbyid/{id}", 1L))
                .andExpect(view().name("tickets"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void getPdf() throws Exception {
        mockMvc.perform(get("/tickets/pdf")
                .param("ticketDate", String.valueOf(LocalDateTime.now().truncatedTo(MINUTES)))
                .param("eventName", "firstName"))
                .andExpect(view().name("ticketPdfView"))
                .andExpect(status().isOk());
    }
}