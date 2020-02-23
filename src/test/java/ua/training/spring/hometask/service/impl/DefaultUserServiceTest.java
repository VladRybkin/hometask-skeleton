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

import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    private static final String USER_EMAIL = "User@gmail.com";

    private static final Long ID = 666L;

    @InjectMocks
    private DefaultUserService userService;

    @Mock
    private UserDao userDao;

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

        User persistedUser = userService.getUserByEmail(USER_EMAIL);

        assertThat(persistedUser, is(testUser));
        verify(userDao).getUserByEmail(USER_EMAIL);
    }

    @Test
    void save() {
        when(userDao.save(testUser)).thenReturn(testUser);

        User persistedUser = userService.save(testUser);

        assertThat(persistedUser, is(testUser));
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

        User persistedUser = userService.getById(ID);

        assertThat(persistedUser, is(testUser));
        verify(userDao).getById(ID);
    }

    @Test
    void getAll() {
        List<User> givenUsers = Lists.newArrayList(testUser);
        when(userDao.getAll()).thenReturn(givenUsers);

        Collection<User> persistedUsers = userService.getAll();

        assertThat(persistedUsers, is(givenUsers));
        verify(userDao).getAll();
    }
}