package ua.training.spring.hometask.facade;

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

import java.time.LocalDate;

public interface UserFacade {

    User saveUser(User user, LocalDate birthday);

    AddUserResponse saveUser(AddUserRequest addUserRequest);

    RemoveUserResponse removeUser(RemoveUserRequest removeUserRequest);

    GetUserByIdResponse getUserById(GetUserByIdRequest getUserByIdRequest);

    GetUserByEmailResponse getUserByEmail(GetUserByEmailRequest getUserByEmailRequest);

    GetAllUsersResponse getAllUsers(GetAllUsersRequest getAllUsersRequest);

    User registerUser(RegistrationUserForm userForm, LocalDate birthday);
}
