package ua.training.spring.hometask.rest.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.dto.rest.response.BookTicketResult;

@Component
public class BookingOperationsClient {

    public BookTicketResult bookTicketRequest(BookTicketParameter bookTicketParameter) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<BookTicketParameter> request = new HttpEntity<>(bookTicketParameter, requestHeaders);

        return restTemplate.postForObject("http://localhost:8888/operations/booking/book", request, BookTicketResult.class);
    }

}
