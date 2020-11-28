package ua.training.spring.hometask.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ua.training.spring.hometask.dto.soap.request.AddEventRequest;
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

    public AddEventResponse addEventResponse(AddEventRequest request) {
        return (AddEventResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public RemoveEventResponse removeEventResponse(RemoveEventRequest request) {
        return (RemoveEventResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }


    public GetEventByIdResponse getEventByIdResponse(GetEventByIdRequest request) {
        return (GetEventByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetEventByNameResponse getEventByNameResponse(GetEventByNameRequest request) {
        return (GetEventByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetAllEventsResponse getAllEventsResponse(GetAllEventsRequest request) {
        return (GetAllEventsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }
}
