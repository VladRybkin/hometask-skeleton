package ua.training.spring.hometask.soapclient;

import org.modelmapper.ModelMapper;
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

    private ModelMapper mapper;

    public AddUserResponse addUserResponse(User user) {
        AddUserRequest request = mapper.map(user, AddUserRequest.class);

        return (AddUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public RemoveUserResponse removeUserResponse(final long id) {
        RemoveUserRequest request = new RemoveUserRequest();
        request.setId(id);

        return (RemoveUserResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }


    public GetUserByIdResponse getUserByIdResponse(long id) {
        GetUserByIdRequest request = new GetUserByIdRequest();
        request.setId(id);

        return (GetUserByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetUserByEmailResponse getUserByEmailResponse(final String email) {
        GetUserByEmailRequest request = new GetUserByEmailRequest();
        request.setEmail(email);

        return (GetUserByEmailResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public GetAllUsersResponse getAllUsersResponse() {
        GetAllUsersRequest request = new GetAllUsersRequest();

        return (GetAllUsersResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
}
