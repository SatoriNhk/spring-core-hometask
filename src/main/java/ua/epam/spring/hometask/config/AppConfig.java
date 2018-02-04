package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.*;
import ua.epam.spring.hometask.repositories.EventRepository;
import ua.epam.spring.hometask.repositories.TicketRepository;
import ua.epam.spring.hometask.repositories.UserRepository;
import ua.epam.spring.hometask.service.*;

@Configuration
@Import(AuditoriumConfig.class)
@ComponentScan({"ua.epam.spring.hometask"})
@EnableAspectJAutoProxy
public class AppConfig {
    /*@Bean
    public EventServiceImpl eventService() {
        return new EventServiceImpl(eventRepository());
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public EventRepository eventRepository() {
        return new EventRepository();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new TicketRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }*/
}
