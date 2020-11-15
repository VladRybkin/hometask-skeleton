package ua.training.spring.hometask.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.training.spring.hometask.dto.soap.UserRequest;
import ua.training.spring.hometask.dto.soap.UserResponse;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8888/schemas/users";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "userRequest")
    @ResponsePayload
    public UserResponse getCountry(@RequestPayload UserRequest userRequest) {
        UserResponse response = new UserResponse();
        response.setFirstName("test");
        response.setEmail("test");
        response.setId(1);
        response.setLastName("44");
        return response;
    }
}
