package ua.training.spring.hometask.testconfig;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ua.training.spring.hometask.facade.BookingFacade;
import ua.training.spring.hometask.facade.TicketFacade;
import ua.training.spring.hometask.facade.UploadFacade;
import ua.training.spring.hometask.facade.UserFacade;
import ua.training.spring.hometask.service.AuditoriumService;
import ua.training.spring.hometask.service.EventService;
import ua.training.spring.hometask.service.TicketService;
import ua.training.spring.hometask.service.UserService;

@Profile("MOCK_BEANS")
@Configuration
public class MockBeans {

    @Bean
    @Primary
    public UserService mockUserService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    @Primary
    public EventService mockEventService() {
        return Mockito.mock(EventService.class);
    }

    @Bean
    @Primary
    public TicketService mockTicketService() {
        return Mockito.mock(TicketService.class);
    }

    @Bean
    @Primary
    public AuditoriumService mockAuditoriumService() {
        return Mockito.mock(AuditoriumService.class);
    }

    @Bean
    @Primary
    public BookingFacade mockBookingFacade() {
        return Mockito.mock(BookingFacade.class);
    }


    @Bean
    @Primary
    public UploadFacade mockUploadFacade() {
        return Mockito.mock(UploadFacade.class);
    }

    @Bean
    @Primary
    public TicketFacade mockTicketFacade() {
        return Mockito.mock(TicketFacade.class);
    }

    @Bean
    @Primary
    public UserFacade mockUserFacade() {
        return Mockito.mock(UserFacade.class);
    }
}
