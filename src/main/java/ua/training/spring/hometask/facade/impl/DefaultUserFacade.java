package ua.training.spring.hometask.facade.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ua.training.spring.hometask.domain.Role;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.dto.RegistrationUserForm;
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
import ua.training.spring.hometask.facade.UserFacade;
import ua.training.spring.hometask.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class DefaultUserFacade implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User saveUser(User user, LocalDate birthday) {
        Validate.notNull(birthday, "user birthday should be present");
        user.setDateOfBirth(birthday.atTime(LocalTime.MIDNIGHT).truncatedTo(DAYS));
        userService.save(user);

        return user;
    }

    @Override
    public User registerUser(RegistrationUserForm userForm, LocalDate dateOfTheBirth) {
        User user = getUserFromUserFormRequest(userForm, dateOfTheBirth);
        userService.save(user);

        return user;
    }

    private User getUserFromUserFormRequest(RegistrationUserForm userForm, LocalDate dateOfTheBirth) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(encoder.encode(userForm.getPassword()));
        user.setFirstName(userForm.getFirstName());
        user.setDateOfBirth(LocalDateTime.of(dateOfTheBirth, LocalTime.MIDNIGHT));
        user.getRoles().add(new Role("USER"));

        return user;
    }

    @Override
    public AddUserResponse saveUser(AddUserRequest addUserRequest) {
        String email = addUserRequest.getEmail();
        Validate.notBlank(email, "email should not be null");

        User user = new User();
        user.setEmail(email);
        user.setFirstName(addUserRequest.getFirstName());
        user.setLastName(addUserRequest.getLastName());
        user.setDateOfBirth(LocalDateTime.ofInstant(addUserRequest.getBirthDay().toInstant(),
                ZoneId.systemDefault()));
        user.setPassword(addUserRequest.getPassword());

        userService.save(user);

        AddUserResponse response = new AddUserResponse();
        UserResponse userResponse = new UserResponse();

        userResponse.setFirstName(user.getFirstName());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setLastName(user.getLastName());
        userResponse.setDateOfBirth(addUserRequest.getBirthDay());

        response.setUserResponse(userResponse);

        return response;
    }

    @Override
    public RemoveUserResponse removeUser(RemoveUserRequest removeUserRequest) {
        Long id = removeUserRequest.getId();
        Validate.notNull(id, "id should not be null");
        User user = userService.getById(id);
        userService.remove(user);

        RemoveUserResponse removeUserResponse = new RemoveUserResponse();
        removeUserResponse.setRemoved(true);

        return removeUserResponse;
    }

    @Override
    public GetUserByIdResponse getUserById(GetUserByIdRequest getUserByIdRequest) {
        Long id = getUserByIdRequest.getId();
        Validate.notNull(id, "id should not be null");
        User user = userService.getById(id);

        GetUserByIdResponse getUserByIdResponse = new GetUserByIdResponse();
        UserResponse userResponse = new UserResponse();

        userResponse.setFirstName(user.getFirstName());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setLastName(user.getLastName());
        userResponse.setDateOfBirth(new Date());
        getUserByIdResponse.setUserResponse(userResponse);

        return getUserByIdResponse;
    }

    @Override
    public GetUserByEmailResponse getUserByEmail(GetUserByEmailRequest getUserByEmailRequest) {
        String email = getUserByEmailRequest.getEmail();
        Validate.notBlank(email, "email should not be null");
        User user = userService.getUserByEmail(email);

        GetUserByEmailResponse getUserByEmailResponse = new GetUserByEmailResponse();
        UserResponse userResponse = new UserResponse();

        userResponse.setFirstName(user.getFirstName());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());
        userResponse.setLastName(user.getLastName());
        userResponse.setDateOfBirth(new Date());
        getUserByEmailResponse.setUserResponse(userResponse);

        return getUserByEmailResponse;
    }

    @Override
    public GetAllUsersResponse getAllUsers(GetAllUsersRequest getAllUsersRequest) {
        GetAllUsersResponse getAllUsersResponse = new GetAllUsersResponse();
        List<User> users = Lists.newArrayList(userService.getAll());
        List<UserResponse> userResponses = Lists.newArrayList();

        users.forEach(user -> {
            UserResponse userResponse = new UserResponse();

            userResponse.setFirstName(user.getFirstName());
            userResponse.setEmail(user.getEmail());
            userResponse.setId(user.getId());
            userResponse.setLastName(user.getLastName());
            userResponse.setDateOfBirth(new Date());
            userResponses.add(userResponse);
        });
        getAllUsersResponse.setUsers(userResponses);

        return getAllUsersResponse;
    }
}
