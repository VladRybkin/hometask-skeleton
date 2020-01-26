package ua.training.spring.hometask.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.training.spring.hometask.exceptions.AuditoriumNotFoundException;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.testconfig.TestBeansAuditorium;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;


@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(classes = TestBeansAuditorium.class)
public class DefaultAuditoriumServiceIntegrationTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void getAll() {
        int expectedAuditoriumsSize = 2;
        assertThat(auditoriumService.getAll(), hasSize(expectedAuditoriumsSize));
    }

    @Test
    public void getByName() {
        String expectedFirstAuditoriumName = "green auditorium";
        String expectedSecondAuditoriumName = "red auditorium";
        assertThat(auditoriumService.getByName(expectedFirstAuditoriumName).getName(), is(expectedFirstAuditoriumName));
        assertThat(auditoriumService.getByName(expectedSecondAuditoriumName).getName(), is(expectedSecondAuditoriumName));
    }

    @Test
    public void ShouldThrowExceptionForIncorrectAuditoriumName() {
        String expectedFirstAuditoriumIncorrectName = "incorrect name";
        assertThrows(AuditoriumNotFoundException.class, () -> {
            auditoriumService.getByName(expectedFirstAuditoriumIncorrectName);
        });
    }

}