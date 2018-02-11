package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

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
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new TicketRepositoryImpl();
    }

    @Bean
    public EventRepository eventRepository() {
        return new EventRepositoryImpl();
    }*/

}
