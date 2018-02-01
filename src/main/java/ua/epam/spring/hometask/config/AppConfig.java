package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.*;
import ua.epam.spring.hometask.repositories.EventRepository;
import ua.epam.spring.hometask.repositories.TicketRepository;
import ua.epam.spring.hometask.repositories.UserRepository;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.EventServiceImpl;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.service.UserServiceImpl;

@Configuration
@Import(AuditoriumConfig.class)
@ComponentScan({"ua.epam.spring.hometask"})
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public EventService eventService() {
        return new EventServiceImpl(eventRepository());
    }

    @Bean
    public UserService userService() {
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
    }
}
