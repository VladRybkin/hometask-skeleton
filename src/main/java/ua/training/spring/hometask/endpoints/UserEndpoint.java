package ua.training.spring.hometask.endpoints;

import com.google.common.collect.Lists;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.training.spring.hometask.dto.soap.request.AddUserRequest;
import ua.training.spring.hometask.dto.soap.request.GetAllUsersRequest;
import ua.training.spring.hometask.dto.soap.request.GetUserByEmailRequest;
import ua.training.spring.hometask.dto.soap.request.GetUserByIdRequest;
import ua.training.spring.hometask.dto.soap.request.RemoveUserRequest;
import ua.training.spring.hometask.dto.soap.request.UserResponse;
import ua.training.spring.hometask.dto.soap.response.AddUserResponse;
import ua.training.spring.hometask.dto.soap.response.GetAllUsersResponse;
import ua.training.spring.hometask.dto.soap.response.GetUserByEmailResponse;
import ua.training.spring.hometask.dto.soap.response.GetUserByIdResponse;
import ua.training.spring.hometask.dto.soap.response.RemoveUserResponse;

import java.util.Date;
import java.util.List;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://localhost:8888/schemas/users";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse saveUser(@RequestPayload AddUserRequest addUserRequest) {
        System.out.println(addUserRequest);
        AddUserResponse response = new AddUserResponse();
        response.setFirstName("test");
        response.setEmail("test");
        response.setId(1);
        response.setLastName("44");
        response.setDate(new Date());

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeUserRequest")
    @ResponsePayload
    public RemoveUserResponse removeUser(@RequestPayload RemoveUserRequest removeUserRequest) {
        System.out.println(removeUserRequest);
        RemoveUserResponse response = new RemoveUserResponse();
        response.setId(1);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest getUserByIdRequest) {
        System.out.println(getUserByIdRequest);

        GetUserByIdResponse response = new GetUserByIdResponse();
        UserResponse userResponse = new UserResponse();

        userResponse.setEmail("getbyirespo");
        userResponse.setFirstName("first name");
        userResponse.setDateOfBirth(new Date());
        response.setUserResponse(userResponse);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByEmailRequest")
    @ResponsePayload
    public GetUserByEmailResponse getUserByEmail(@RequestPayload GetUserByEmailRequest getUserByEmailRequest) {
        System.out.println(getUserByEmailRequest);

        GetUserByEmailResponse response = new GetUserByEmailResponse();
        UserResponse userResponse = new UserResponse();

        userResponse.setEmail("getbyidemailemail");
        userResponse.setFirstName("first name");
        userResponse.setDateOfBirth(new Date());
        response.setUserResponse(userResponse);

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers(@RequestPayload GetAllUsersRequest retAllUsersRequest) {

        GetAllUsersResponse response = new GetAllUsersResponse();
        UserResponse user1 = new UserResponse();

        user1.setEmail("test");
        UserResponse user2 = new UserResponse();

        user2.setEmail("test");
        List<UserResponse> list = Lists.newArrayList(user1, user2);
        response.getUsers().addAll(list);

        return response;
    }
}
