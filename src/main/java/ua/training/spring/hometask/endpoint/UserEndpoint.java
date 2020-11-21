package ua.training.spring.hometask.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.training.spring.hometask.dto.soap.request.AddUserRequest;
import ua.training.spring.hometask.dto.soap.request.GetAllUsersRequest;
import ua.training.spring.hometask.dto.soap.request.GetUserByEmailRequest;
import ua.training.spring.hometask.dto.soap.request.GetUserByIdRequest;
import ua.training.spring.hometask.dto.soap.request.RemoveUserRequest;
import ua.training.spring.hometask.dto.soap.response.AddUserResponse;
import ua.training.spring.hometask.dto.soap.response.GetAllUsersResponse;
import ua.training.spring.hometask.dto.soap.response.GetUserByEmailResponse;
import ua.training.spring.hometask.dto.soap.response.GetUserByIdResponse;
import ua.training.spring.hometask.dto.soap.response.RemoveUserResponse;
import ua.training.spring.hometask.facade.UserFacade;

@Endpoint
public class UserEndpoint {

    @Autowired
    private UserFacade userFacade;

    private static final String NAMESPACE_URI = "http://training/schemas/hometask";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse saveUser(@RequestPayload AddUserRequest addUserRequest) {
        return userFacade.saveUser(addUserRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeUserRequest")
    @ResponsePayload
    public RemoveUserResponse removeUser(@RequestPayload RemoveUserRequest removeUserRequest) {
        return userFacade.removeUser(removeUserRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest getUserByIdRequest) {
        return userFacade.getUserById(getUserByIdRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByEmailRequest")
    @ResponsePayload
    public GetUserByEmailResponse getUserByEmail(@RequestPayload GetUserByEmailRequest getUserByEmailRequest) {
        return userFacade.getUserByEmail(getUserByEmailRequest);
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest getAllUsersRequest) {
        return userFacade.getAllUsers(getAllUsersRequest);
    }
}
