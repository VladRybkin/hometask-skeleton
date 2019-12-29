package ua.training.spring.hometask.service.impl;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ua.training.spring.hometask.service.AuditoriumService;


@ExtendWith(MockitoExtension.class)
@ContextConfiguration("/Auditorium.xml")
class DefaultAuditoriumServiceTest {

    @Autowired
    AuditoriumService auditoriumService;

    @Test
    void getAll() {

    }

    @Test
    void getByName() {
    }
}