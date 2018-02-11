package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repositories.UserRepositoryImpl;

import java.sql.SQLException;
import java.time.LocalDate;

public class Application {
    public static void main(String[] args) throws SQLException {

        //ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserRepositoryImpl userRepository = ctx.getBean(UserRepositoryImpl.class);
        User user = new User();
        user.setId(1L);
        user.setFirstName("Anna");
        user.setLastName("Malahova");
        user.setEmail("annaMalah@gmail.com");
        user.setDateOfBirth(LocalDate.of(2017, 10, 11));
        userRepository.save(user);

        System.out.println(userRepository.getUserByEmail("annaMalah@gmail.com").getFirstName());

    }
}
