package ua.training.spring.hometask.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class TestEvent {

    private Event event;

    @BeforeEach
     void initEvent() {
        event = new Event();
        event.setBasePrice(1.1);
        event.setName("aaa");
        event.setRating(EventRating.HIGH);

        LocalDateTime now = LocalDateTime.now();

        event.addAirDateTime(now);
        event.addAirDateTime(now.plusDays(1));
        event.addAirDateTime(now.plusDays(2));
    }

    @Test
    public void testAddRemoveAirDates() {
        int size = event.getAirDates().size();

        LocalDateTime date = LocalDateTime.now().plusDays(5);

        event.addAirDateTime(date);

        assertThat(event.getAirDates(), hasSize(size + 1));
        assertThat(event.getAirDates(), hasItems(date));

        event.removeAirDateTime(date);

        assertThat(event.getAirDates(), hasSize(size));
        assertThat(event.getAirDates(), not(hasItems(date)));
    }

    @Test
    public void testCheckAirDates() {
        assertThat(event.airsOnDate(LocalDate.now()), is(true));
        assertThat(event.airsOnDate(LocalDate.now().plusDays(1)), is(true));
        assertThat(event.airsOnDate(LocalDate.now().minusDays(10)), is(false));

        assertThat(event.airsOnDates(LocalDate.now(), LocalDate.now().plusDays(10)), is(true));
        assertThat(event.airsOnDates(LocalDate.now().minusDays(10), LocalDate.now().plusDays(10)), is(true));
        assertThat(event.airsOnDates(LocalDate.now().plusDays(1), LocalDate.now().plusDays(1)), is(true));
        assertThat(event.airsOnDates(LocalDate.now().minusDays(10), LocalDate.now().minusDays(5)), is(false));

        LocalDateTime time = LocalDateTime.now().plusHours(4);
        event.addAirDateTime(time);
        assertThat(event.airsOnDateTime(time), is(true));
        time = time.plusHours(30);
        assertThat(event.airsOnDateTime(time), is(false));
    }

    @Test
    public void testAddRemoveAuditoriums() {
        LocalDateTime time = event.getAirDates().first();

        assertThat(event.getAuditoriums(), is(anEmptyMap()));

        event.assignAuditorium(time, new Auditorium());

        assertThat(event.getAuditoriums(), not(is(anEmptyMap())));

        event.removeAuditoriumAssignment(time);

        assertThat(event.getAuditoriums(), is(anEmptyMap()));
    }

    @Test
    public void testAddRemoveAuditoriumsWithAirDates() {

        LocalDateTime time = LocalDateTime.now().plusDays(10);

        assertThat(event.getAuditoriums(), is(anEmptyMap()));

        event.addAirDateTime(time, new Auditorium());

        assertThat(event.getAuditoriums(), not(is(anEmptyMap())));

        event.removeAirDateTime(time);

        assertThat(event.getAuditoriums(), is(anEmptyMap()));
    }

    @Test
    public void testNotAddAuditoriumWithoutCorrectDate() {
        LocalDateTime time = LocalDateTime.now().plusDays(10);

        boolean result = event.assignAuditorium(time, new Auditorium());

        assertThat(result, is(false));
        assertThat(event.getAuditoriums(), is(anEmptyMap()));

        result = event.removeAirDateTime(time);
        assertThat(result, is(false));

        assertThat(event.getAuditoriums(), is(anEmptyMap()));
    }

}
