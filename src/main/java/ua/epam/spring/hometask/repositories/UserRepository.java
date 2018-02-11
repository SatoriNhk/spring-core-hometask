package ua.epam.spring.hometask.repositories;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public interface UserRepository extends AbstractRepository<User> {

    @Nullable
    User getUserByEmail(@Nonnull String email);
}
