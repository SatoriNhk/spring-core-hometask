package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repositories.UserRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userRepository.getAll()
                .stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(@Nonnull User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        userRepository.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return  userRepository.getAll()
                .stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst()
                .orElse(null);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }
}
