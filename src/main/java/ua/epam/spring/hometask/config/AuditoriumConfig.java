package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Auditorium;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableAspectJAutoProxy
//@PropertySource("classpath:properties/auditoriums.properties")
public class AuditoriumConfig {

    static private Properties properties = new Properties();

    static {
        try(InputStream resourceAsStream = AuditoriumConfig.class.getResourceAsStream(("/properties/auditoriums.properties"))) {
            properties.load(resourceAsStream);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Bean
    static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Component
    static class AuditoriumOne extends Auditorium {

        public AuditoriumOne() {
            init();
        }

        void init() {
           setName(properties.getProperty("auditorium1.name"));
           setVipSeats(Arrays.stream(properties.getProperty("auditorium1.vipSeats")
                   .split(","))
                   .map(Long::valueOf)
                   .collect(Collectors.toSet()));
           setNumberOfSeats(Long.valueOf(properties.getProperty("auditorium1.numberOfSeats")));
        }
    }

    @Component
    static class AuditoriumTwo extends Auditorium {

        public AuditoriumTwo() {
            init();
        }

        void init() {
            setName(properties.getProperty("auditorium2.name"));
            setVipSeats(Arrays.stream(properties.getProperty("auditorium2.vipSeats")
                    .split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toSet()));
            setNumberOfSeats(Long.valueOf(properties.getProperty("auditorium2.numberOfSeats")));
        }
    }

    @Component
    static class AuditoriumThree extends Auditorium {

        public AuditoriumThree() {
            init();
        }

        void init() {
            setName(properties.getProperty("auditorium3.name"));
            setVipSeats(Arrays.stream(properties.getProperty("auditorium3.vipSeats")
                    .split(","))
                    .map(Long::valueOf)
                    .collect(Collectors.toSet()));
            setNumberOfSeats(Long.valueOf(properties.getProperty("auditorium3.numberOfSeats")));
        }
    }

}
