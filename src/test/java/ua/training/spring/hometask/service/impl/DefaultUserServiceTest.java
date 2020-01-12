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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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
        testUser = new User();
        testUser.setId(ID);
        testUser.setEmail(USER_EMAIL);
    }


    @Test
    void getUserByEmail() throws Exception {
        when(userDao.getUserByEmail(USER_EMAIL)).thenReturn(testUser);
        assertThat(userService.getUserByEmail(USER_EMAIL), is(testUser));
        verify(userDao).getUserByEmail(USER_EMAIL);
    }

    @Test
    void save() {
        when(userDao.save(testUser)).thenReturn(testUser);
        assertThat(userService.save(testUser), is(testUser));
        verify(userDao).save(testUser);
    }

    @Test
    void remove() {
        userService.remove(testUser);
        verify(userDao).remove(testUser);
    }

    @Test
    void getById() {
        when(userDao.getById(ID)).thenReturn(testUser);
        userService.getById(ID);
        verify(userDao).getById(ID);
    }

    @Test
    void getAll() {
        List<User>users=Lists.newArrayList();
        when(userDao.getAll()).thenReturn(users);
        assertThat(userService.getAll(), is(users));
        verify(userDao).getAll();
    }
}