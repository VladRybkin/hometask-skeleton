package ua.training.spring.hometask.service;

import ua.training.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public interface UserService extends AbstractDomainObjectService<User> {

    @Nullable
    User getUserByEmail(@Nonnull String email) throws Exception;

}
