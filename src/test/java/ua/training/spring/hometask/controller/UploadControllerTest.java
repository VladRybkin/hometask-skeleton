package ua.training.spring.hometask.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.training.spring.hometask.config.WebMvcConfig;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"TEST", "MOCK_BEANS", "IN_MEMORY"})
@WebAppConfiguration
class UploadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void getUpload() throws Exception {
        mockMvc.perform(get("/upload"))
                .andExpect(view().name("upload"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "test", authorities = { "BOOKING_MANAGER" })
    void upload() throws Exception {
        MockMultipartFile multipartFile = mock(MockMultipartFile.class);

        mockMvc.perform(post("/upload")
                .requestAttr("jsonFile", multipartFile))
                .andExpect(redirectedUrl("/upload"))
                .andExpect(status().is3xxRedirection());
    }
}
