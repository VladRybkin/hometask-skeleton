package ua.training.spring.hometask.domain;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


public class TestAuditorium {

    @Test
    public void testCountVips() {
        Auditorium a = new Auditorium();
        a.setVipSeats(Stream.of("1", "2", "3").collect(Collectors.toSet()));
        assertThat(a.countVipSeats(Arrays.asList(10L, 20L, 30L)), is(0L));
        assertThat(a.countVipSeats(Arrays.asList(10L, 2L, 30L)), is(1L));
        assertThat(a.countVipSeats(Arrays.asList(10L, 2L, 3L, 4L, 5L, 6L)), is(2L));
    }

    public void testGetAllSeats() {
        Auditorium a = new Auditorium();
        a.setNumberOfSeats(10);
        assertThat(a.getAllSeats(), hasSize(10));
    }

}
