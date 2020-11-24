package ua.training.spring.hometask.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ua.training.spring.hometask.domain.Event;
import ua.training.spring.hometask.dto.soap.request.AddUserRequest;
import ua.training.spring.hometask.dto.soap.request.GetAllEventsRequest;
import ua.training.spring.hometask.dto.soap.request.GetEventByIdRequest;
import ua.training.spring.hometask.dto.soap.request.GetEventByNameRequest;
import ua.training.spring.hometask.dto.soap.request.RemoveEventRequest;
import ua.training.spring.hometask.dto.soap.response.AddEventResponse;
import ua.training.spring.hometask.dto.soap.response.GetAllEventsResponse;
import ua.training.spring.hometask.dto.soap.response.GetEventByIdResponse;
import ua.training.spring.hometask.dto.soap.response.GetEventByNameResponse;
import ua.training.spring.hometask.dto.soap.response.RemoveEventResponse;

public class EventClient extends WebServiceGatewaySupport {

    public AddEventResponse addEventResponse(Event event) {
        AddUserRequest request = new AddUserRequest();

        AddEventResponse response = (AddEventResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    public RemoveEventResponse removeEventResponse(long id) {
        RemoveEventRequest request = new RemoveEventRequest();
        request.setId(id);

        RemoveEventResponse response = (RemoveEventResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }


    public GetEventByIdResponse getEventByIdResponse(long id) {
        GetEventByIdRequest request = new GetEventByIdRequest();
        request.setId(id);

        GetEventByIdResponse response = (GetEventByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    public GetEventByNameResponse getEventByNameResponse(String eventName) {
        GetEventByNameRequest request = new GetEventByNameRequest();
        request.setEventName(eventName);

        GetEventByNameResponse response = (GetEventByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    public GetAllEventsResponse getAllEventsResponse() {
        GetAllEventsRequest request = new GetAllEventsRequest();

        GetAllEventsResponse response = (GetAllEventsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }
}
