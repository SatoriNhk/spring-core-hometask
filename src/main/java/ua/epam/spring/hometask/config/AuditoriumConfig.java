package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:properties/auditoriums.properties")
public class AuditoriumConfig {

    @Value("${auditorium1.name}")
    private String name1;
    @Value("${auditorium1.numberOfSeats}")
    private String numberOfSeats1;
    @Value("${auditorium1.vipSeats}")
    private String vipSeats1;

    @Value("${auditorium2.name}")
    private String name2;
    @Value("${auditorium2.numberOfSeats}")
    private String numberOfSeats2;
    @Value("${auditorium2.vipSeats}")
    private String vipSeats2;

    @Value("${auditorium3.name}")
    private String name3;
    @Value("${auditorium3.numberOfSeats}")
    private String numberOfSeats3;
    @Value("${auditorium3.vipSeats}")
    private String vipSeats3;


    @Bean
    static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }


    @Bean
    public Auditorium auditorium2() {
        return new Auditorium(name2,
                Long.valueOf(numberOfSeats2),
                Arrays.stream(vipSeats2.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toSet()));
    }

    @Bean
    public Auditorium auditorium3() {
        return new Auditorium(name3,
                Long.valueOf(numberOfSeats3),
                Arrays.stream(vipSeats3.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toSet()));
    }

    @Bean
    public Auditorium auditorium1() {
        return new Auditorium(name1,
                Long.valueOf(numberOfSeats1),
                Arrays.stream(vipSeats1.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toSet()));
    }

    /*@Bean
    public Set<Auditorium> auditoriums() {
        Set<Auditorium> set = new HashSet<>();
        Auditorium auditorium1 = new Auditorium(name1,
                Long.valueOf(numberOfSeats1),
                Arrays.stream(vipSeats1.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toSet()));
        Auditorium auditorium2 = new Auditorium(name2,
                Long.valueOf(numberOfSeats2),
                Arrays.stream(vipSeats2.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toSet()));
        Auditorium auditorium3 = new Auditorium(name3,
                Long.valueOf(numberOfSeats3),
                Arrays.stream(vipSeats3.split(","))
                        .map(Long::valueOf)
                        .collect(Collectors.toSet()));
        set.addAll(Arrays.asList(auditorium1, auditorium2, auditorium3));
        return set;
    }

    @Bean
    public AuditoriumServiceImpl auditoriumServiceImpl() {
        return new AuditoriumServiceImpl(auditoriums());
    }*/

}
