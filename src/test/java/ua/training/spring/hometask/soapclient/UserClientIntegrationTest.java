package ua.training.spring.hometask.soapclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
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
import ua.training.spring.hometask.dto.soap.response.UserResponse;
import ua.training.spring.hometask.init.InitApplication;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
class UserClientIntegrationTest {

    @Autowired
    private UserClient client;

    @Test
    void addUser() {
        AddUserRequest userRequest = prepareAddUserRequest();

        AddUserResponse addUserResponse = client.addUserResponse(userRequest);
        UserResponse userResponse = addUserResponse.getUserResponse();

        assertThat(userResponse.getFirstName(), is(userRequest.getFirstName()));
        assertThat(userResponse.getLastName(), is(userRequest.getLastName()));
        assertThat(userResponse.getEmail(), is(userRequest.getEmail()));
        assertThat(userResponse.getPassword(), is(userRequest.getPassword()));
        assertThat(userResponse.getDateOfBirth(), is(userRequest.getDateOfBirth()));

        SendRemoveUserRequestToClearUser(addUserResponse);
    }

    @Test
    void removeUser() {
        AddUserResponse addUserResponse = prepareAndSendAddUserRequest();

        RemoveUserRequest removeUserRequest = new RemoveUserRequest();
        removeUserRequest.setId(addUserResponse.getUserResponse().getId());

        RemoveUserResponse removeUserResponse = client.removeUserResponse(removeUserRequest);

        assertThat(removeUserResponse.isRemoved(), is(true));
    }


    @Test
    void getUserByEmail() {
        AddUserResponse addUserResponse = prepareAndSendAddUserRequest();
        GetUserByEmailRequest getUserByEmailRequest = new GetUserByEmailRequest();
        getUserByEmailRequest.setEmail(addUserResponse.getUserResponse().getEmail());

        GetUserByEmailResponse getUserByEmailResponse = client.getUserByEmailResponse(getUserByEmailRequest);
        UserResponse userResponse = getUserByEmailResponse.getUserResponse();

        assertThat(userResponse.getEmail(), is(addUserResponse.getUserResponse().getEmail()));
    }

    @Test
    void getUserById() {
        AddUserResponse addUserResponse = prepareAndSendAddUserRequest();
        GetUserByIdRequest getUserByIdRequest = new GetUserByIdRequest();
        getUserByIdRequest.setId(addUserResponse.getUserResponse().getId());

        GetUserByIdResponse getUserByIdResponse = client.getUserByIdResponse(getUserByIdRequest);
        UserResponse userResponse = getUserByIdResponse.getUserResponse();

        assertThat(userResponse.getId(), is(addUserResponse.getUserResponse().getId()));

    }


    @Test
    void getAllUsers() {
        GetAllUsersRequest getAllUsersRequest = new GetAllUsersRequest();
        GetAllUsersResponse getAllUsersResponse = client.getAllUsersResponse(getAllUsersRequest);

        List<UserResponse> userResponses = getAllUsersResponse.getUsers();

        assertThat(userResponses, not(empty()));
    }


    private AddUserRequest prepareAddUserRequest() {
        AddUserRequest userRequest = new AddUserRequest();
        String userEmail = "TestEmail";
        userRequest.setFirstName("test first name");
        userRequest.setLastName("test last name");
        userRequest.setPassword("test password");
        userRequest.setEmail(userEmail);
        userRequest.setDateOfBirth(LocalDateTime.now());

        return userRequest;
    }

    private AddUserResponse prepareAndSendAddUserRequest() {
        AddUserRequest userRequest = prepareAddUserRequest();

        return client.addUserResponse(userRequest);
    }


    private void SendRemoveUserRequestToClearUser(AddUserResponse addUserResponse) {
        RemoveUserRequest removeUserRequest = new RemoveUserRequest();
        removeUserRequest.setId(addUserResponse.getUserResponse().getId());
        client.removeUserResponse(removeUserRequest);
    }
}
