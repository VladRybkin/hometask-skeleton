package ua.training.spring.hometask.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.training.spring.hometask.exceptions.AuditoriumNotFoundException;
import ua.training.spring.hometask.service.AuditoriumService;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/test-context.xml")
class DefaultAuditoriumServiceIntegrationTest {


    @Autowired
    private AuditoriumService auditoriumService;


    @Test
    void getAll() {
        int expectedAuditoriumsSize = 2;
        assertThat(auditoriumService.getAll().size(), is(expectedAuditoriumsSize));


    }

    @Test
    void getByName() {
        String expectedFirstAuditoriumName = "The first auditorium";
        String expectedSecondAuditoriumName = "The second auditorium";
        assertThat(auditoriumService.getByName(expectedFirstAuditoriumName).getName(), is(expectedFirstAuditoriumName));
        assertThat(auditoriumService.getByName(expectedSecondAuditoriumName).getName(), is(expectedSecondAuditoriumName));
    }

    @Test
    void ShouldThrowExceptionForIncorrectAuditoriumName() {
        String expectedFirstAuditoriumIncorrectName = "incorrect name";
        assertThrows(AuditoriumNotFoundException.class, () -> {
            auditoriumService.getByName(expectedFirstAuditoriumIncorrectName);
        });
    }

}