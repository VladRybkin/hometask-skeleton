package ua.training.spring.hometask.controller;

import com.google.common.collect.Sets;
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
import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.service.AuditoriumService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeansConfiguration.class)
@ActiveProfiles({"WEB_MVC", "TEST", "MOCK_BEANS", "IN_MEMORY"})
@WebAppConfiguration
class AuditoriumControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AuditoriumService auditoriumService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getAuditoriums() throws Exception {
        mockMvc.perform(get("/auditoriums"))
                .andExpect(status().isOk())
                .andExpect(view().name("auditoriums"))
                .andExpect(model().attributeExists("auditoriums"));
    }

    @Test
    void getByName() throws Exception {
        Auditorium auditorium = new Auditorium();
        auditorium.setName("test green auditorium");
        auditorium.setNumberOfSeats(10);
        auditorium.setVipSeats(Sets.newHashSet("1", "2"));

        when(auditoriumService.getByName("test green auditorium")).thenReturn(auditorium);

        mockMvc.perform(get("/auditoriums/getbyname/")
                .param("name", "test green auditorium"))
                .andExpect(view().name("auditoriums"))
                .andExpect(status().isOk());
    }
}