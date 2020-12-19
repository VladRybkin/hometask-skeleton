package ua.training.spring.hometask.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.dto.rest.response.BookTicketResult;

@Component
public class BookingOperationsClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders jsonRequestHeaders;

    public void bookTicketRequest(BookTicketParameter bookTicketParameter) {
        HttpEntity<BookTicketParameter> request = new HttpEntity<>(bookTicketParameter, jsonRequestHeaders);
        restTemplate.put("http://localhost:8888/operations/booking/book", request, BookTicketResult.class);
    }

}
