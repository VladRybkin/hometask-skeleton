package ua.training.spring.hometask.service.impl;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.spring.hometask.dao.UserDao;
import ua.training.spring.hometask.domain.User;
import ua.training.spring.hometask.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @InjectMocks
    private UserService userService = new DefaultUserService();

    @Mock
    private UserDao userDao;

    private static final String USER_EMAIL = "User@gmail.com";

    private static final Long ID = 666L;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User(USER_EMAIL);
    }


    @Test
    void getUserByEmail() throws Exception {
        when(userService.getUserByEmail(USER_EMAIL)).thenReturn(testUser);
        userService.getUserByEmail(USER_EMAIL);
        verify(userDao).getUserByEmail(USER_EMAIL);
    }

    @Test
    void save() {
        when(userService.save(testUser)).thenReturn(testUser);
        userService.save(testUser);
        verify(userDao).save(testUser);
    }

    @Test
    void remove() {
        userService.remove(testUser);
        verify(userDao).remove(testUser);
    }

    @Test
    void getById() {
        when(userService.getById(ID)).thenReturn(testUser);
        userService.getById(ID);
        verify(userDao).getById(ID);
    }

    @Test
    void getAll() {
        List<User> users = Lists.newArrayList();
        when(userService.getAll()).thenReturn(users);
        userService.getAll();
        verify(userDao).getAll();
    }
}