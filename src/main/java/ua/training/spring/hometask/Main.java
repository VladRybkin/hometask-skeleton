package ua.training.spring.hometask;

import ua.training.spring.hometask.dto.rest.request.BookTicketParameter;
import ua.training.spring.hometask.rest.client.BookingOperationsClient;
import ua.training.spring.hometask.rest.client.UserOperationsClient;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BookTicketParameter bookTicketParameter=new BookTicketParameter();
        bookTicketParameter.setTicketId(1l);
        bookTicketParameter.setUserEmail("useremail");
        System.out.println(new UserOperationsClient().getUserByEmailRequest("VladTV@mail"));
//        Bootstrap.main(args);
    }
}
