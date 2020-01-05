package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import ua.training.spring.hometask.domain.Auditorium;
import ua.training.spring.hometask.service.AuditoriumService;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class DefaultAuditoriumServiceTest {


    private AuditoriumService auditoriumService;

    private Set<Auditorium> auditoriums;

    private Auditorium firstAuditorium;

    private Auditorium secondAuditorium;

    private static final String FIRST_AUDITORIUM_NAME = "first";

    private static final String SECOND_AUDITORIUM_NAME = "second";

    @BeforeEach()
    void setUp() {
        firstAuditorium = new Auditorium(FIRST_AUDITORIUM_NAME, 60, Sets.newHashSet(1L, 2L));
        secondAuditorium = new Auditorium(SECOND_AUDITORIUM_NAME, 50, Sets.newHashSet(3L, 4L));
        auditoriums = Sets.newHashSet(firstAuditorium, secondAuditorium);
        auditoriumService = new DefaultAuditoriumService(auditoriums);
    }

    @Test
    void getAll() {
        assertThat(auditoriumService.getAll(), containsInAnyOrder(auditoriums.toArray()));
    }

    @Test
    void getByName() {
        assertEquals(auditoriumService.getByName(FIRST_AUDITORIUM_NAME), firstAuditorium);
        assertEquals(auditoriumService.getByName(SECOND_AUDITORIUM_NAME), secondAuditorium);
    }
}