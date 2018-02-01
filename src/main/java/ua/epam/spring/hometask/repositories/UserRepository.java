package ua.epam.spring.hometask.repositories;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRepository {

    private static Set<User> users = new HashSet<>();

    public Set<User> getAll() {
        return users;
    }

    public void remove(User user){
        users.remove(user);
    }

    public void save(User user){
        users.add(user);
    }
}
