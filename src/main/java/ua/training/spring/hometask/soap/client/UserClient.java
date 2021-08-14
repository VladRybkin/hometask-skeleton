package ua.training.spring.hometask.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
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

public class UserClient extends WebServiceGatewaySupport {

    public AddUserResponse addUserResponse(AddUserRequest userRequest) {
        return (AddUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(userRequest);
    }

    public RemoveUserResponse removeUserResponse(RemoveUserRequest request) {
        return (RemoveUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }


    public GetUserByIdResponse getUserByIdResponse(GetUserByIdRequest request) {
        return (GetUserByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetUserByEmailResponse getUserByEmailResponse(GetUserByEmailRequest request) {
        return (GetUserByEmailResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetAllUsersResponse getAllUsersResponse(GetAllUsersRequest request) {
        return (GetAllUsersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }
}
