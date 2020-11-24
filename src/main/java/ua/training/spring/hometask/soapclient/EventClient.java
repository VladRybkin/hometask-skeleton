package ua.training.spring.hometask.soapclient;

import org.modelmapper.ModelMapper;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ua.training.spring.hometask.domain.Event;
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

    private ModelMapper mapper;

    public AddEventResponse addEventResponse(Event event) {
        AddEventRequest request = mapper.map(event, AddEventRequest.class);

        return (AddEventResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public RemoveEventResponse removeEventResponse(long id) {
        RemoveEventRequest request = new RemoveEventRequest();
        request.setId(id);

        return (RemoveEventResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }


    public GetEventByIdResponse getEventByIdResponse(long id) {
        GetEventByIdRequest request = new GetEventByIdRequest();
        request.setId(id);

        return (GetEventByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetEventByNameResponse getEventByNameResponse(String eventName) {
        GetEventByNameRequest request = new GetEventByNameRequest();
        request.setEventName(eventName);

        return (GetEventByNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetAllEventsResponse getAllEventsResponse() {
        GetAllEventsRequest request = new GetAllEventsRequest();

        return (GetAllEventsResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
}
