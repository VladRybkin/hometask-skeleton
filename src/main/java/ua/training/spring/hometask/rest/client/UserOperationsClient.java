package ua.training.spring.hometask.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.training.spring.hometask.dto.rest.request.AddUserParameter;
import ua.training.spring.hometask.dto.rest.response.AddUserResult;
import ua.training.spring.hometask.dto.rest.response.UserResponse;

@Component
public class UserOperationsClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders jsonRequestHeaders;

    public AddUserResult addUserRequest(AddUserParameter addUserParameter) {
        HttpEntity<AddUserParameter> request = new HttpEntity<>(addUserParameter, jsonRequestHeaders);

        return restTemplate.postForObject("http://localhost:8888/operations/users/add", request, AddUserResult.class);
    }

    public UserResponse getUserByIdRequest(long id) {
        return restTemplate.getForObject("http://localhost:8888/operations/users/getbyid/" + id, UserResponse.class, jsonRequestHeaders);
    }

    public void removeUserByIdRequest(long id) {
        restTemplate.delete("http://localhost:8888/operations/users/remove/" + id);
    }

    public UserResponse getUserByEmailRequest(String email) {
        return restTemplate.getForObject("http://localhost:8888/operations/users/getbyemail?email=" + email, UserResponse.class, jsonRequestHeaders);
    }

    public UserResponse[] getAllUsersRequest() {
        return restTemplate.getForObject("http://localhost:8888/operations/users/getAll", UserResponse[].class, jsonRequestHeaders);
    }
}
