package ua.training.spring.hometask.rest.client;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.training.spring.hometask.config.WebMvcConfig;
import ua.training.spring.hometask.dto.rest.request.AddUserParameter;
import ua.training.spring.hometask.dto.rest.response.AddUserResult;
import ua.training.spring.hometask.dto.rest.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebMvcConfig.class)
@ActiveProfiles({"IN_MEMORY", "TEST"})
@WebAppConfiguration
class UserOperationsClientIntegrationTest {

    @Autowired
    private UserOperationsClient userOperationsClient;

    @Test
    void addUserRequest() {
        AddUserParameter addUserParameter = new AddUserParameter();
        addUserParameter.setFirstName("testEvent");
        addUserParameter.setEmail("testEmail");
        addUserParameter.setLastName("LastName");
        addUserParameter.setPassword("testPsass");
        addUserParameter.setDateOfBirth(LocalDateTime.now().truncatedTo(MINUTES));

        AddUserResult addUserResult = userOperationsClient.addUserRequest(addUserParameter);

        assertThat(addUserResult.getFirstName(), is(addUserParameter.getFirstName()));
        assertThat(addUserResult.getEmail(), is(addUserParameter.getEmail()));
        assertThat(addUserResult.getLastName(), is(addUserParameter.getLastName()));
        assertThat(addUserResult.getDateOfBirth(), is(addUserParameter.getDateOfBirth()));
        assertThat(addUserResult.getPassword(), not(nullValue()));
        assertThat(addUserResult.getId(), not(nullValue()));
    }

    @Test
    void getUserByIdRequest() {
        AddUserParameter addUserParameter = new AddUserParameter();
        addUserParameter.setFirstName("testEvent");
        addUserParameter.setEmail("testEmail");
        addUserParameter.setPassword("testPsass");
        addUserParameter.setLastName("LastName");
        addUserParameter.setDateOfBirth(LocalDateTime.now().truncatedTo(MINUTES));


        AddUserResult addUserResult = userOperationsClient.addUserRequest(addUserParameter);
        UserResponse userResponse = userOperationsClient.getUserByIdRequest(addUserResult.getId());

        assertThat(userResponse.getFirstName(), is(addUserParameter.getFirstName()));
        assertThat(userResponse.getEmail(), is(addUserParameter.getEmail()));
        assertThat(userResponse.getLastName(), is(addUserParameter.getLastName()));
        assertThat(addUserResult.getDateOfBirth(), is(addUserParameter.getDateOfBirth()));
        assertThat(userResponse.getId(), not(nullValue()));
    }

    @Test
    void removeUserByIdRequest() {
        AddUserParameter addUserParameter = new AddUserParameter();
        addUserParameter.setFirstName("testEvent");
        addUserParameter.setEmail("testEmail");
        addUserParameter.setPassword("testPsass");
        addUserParameter.setLastName("LastName");
        addUserParameter.setDateOfBirth(LocalDateTime.now());

        AddUserResult addUserResult = userOperationsClient.addUserRequest(addUserParameter);
        userOperationsClient.removeUserByIdRequest(addUserResult.getId());

        assertThat(userOperationsClient.getUserByIdRequest(addUserResult.getId()), nullValue());
    }

    @Test
    void getUserByEmailRequest() {
        AddUserParameter addUserParameter = new AddUserParameter();
        addUserParameter.setFirstName("testEvent");
        addUserParameter.setEmail("testEmail");
        addUserParameter.setPassword("testPsass");
        addUserParameter.setLastName("LastName");
        addUserParameter.setDateOfBirth(LocalDateTime.now().truncatedTo(MINUTES));

        AddUserResult addUserResult = userOperationsClient.addUserRequest(addUserParameter);
        UserResponse userResponse = userOperationsClient.getUserByEmailRequest(addUserResult.getEmail());

        assertThat(userResponse.getFirstName(), is(addUserParameter.getFirstName()));
        assertThat(userResponse.getEmail(), is(addUserParameter.getEmail()));
        assertThat(userResponse.getLastName(), is(addUserParameter.getLastName()));
        assertThat(addUserResult.getDateOfBirth(), is(addUserParameter.getDateOfBirth()));
        assertThat(userResponse.getId(), not(nullValue()));
    }

    @Test
    void getAllUsersRequest() {
        AddUserParameter addUserParameter = new AddUserParameter();
        addUserParameter.setFirstName("testEvent");
        addUserParameter.setEmail("testEmail");
        addUserParameter.setPassword("testPsass");
        addUserParameter.setLastName("LastName");
        addUserParameter.setDateOfBirth(LocalDateTime.now().truncatedTo(MINUTES));

        userOperationsClient.addUserRequest(addUserParameter);

        List<UserResponse> userResponses = Lists.newArrayList(userOperationsClient.getAllUsersRequest());

        assertThat(userResponses, not(empty()));
    }
}