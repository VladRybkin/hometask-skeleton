package ua.training.spring.hometask.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ua.training.spring.hometask.domain.User;
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

    public AddUserResponse addUserResponse(User user) {
        AddUserRequest request = new AddUserRequest();
        request.setDateOfBirth(user.getDateOfBirth());
        request.setEmail(user.getEmail());
        request.setFirstName(user.getFirstName());
        request.setLastName(user.getLastName());
        request.setPassword(user.getPassword());

        AddUserResponse response = (AddUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    public RemoveUserResponse removeUserResponse(final long id) {
        RemoveUserRequest request = new RemoveUserRequest();
        request.setId(id);

        RemoveUserResponse response = (RemoveUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }


    public GetUserByIdResponse getUserByIdResponse(long id) {
        GetUserByIdRequest request = new GetUserByIdRequest();
        request.setId(id);

        GetUserByIdResponse response = (GetUserByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    public GetUserByEmailResponse getUserByEmailResponse(final String email) {
        GetUserByEmailRequest request = new GetUserByEmailRequest();
        request.setEmail(email);

        GetUserByEmailResponse response = (GetUserByEmailResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }

    public GetAllUsersResponse getAllUsersResponse() {
        GetAllUsersRequest request = new GetAllUsersRequest();

        GetAllUsersResponse response = (GetAllUsersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);

        return response;
    }
}
