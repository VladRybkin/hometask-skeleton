package ua.training.spring.hometask.rest.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
class BookingOperationsClientTest {

    @Autowired
    private BookingOperationsClient bookingOperationsClient;

    @Test
    void bookTicketRequest() {
        BookTicketParameter bookTicketParameter = new BookTicketParameter();
        bookTicketParameter.setUserEmail("VladTv@mail");
        bookTicketParameter.setTicketId(1L);

        bookingOperationsClient.bookTicketRequest(bookTicketParameter);
    }
}