package ua.epam.spring.hometask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractTest {

    @Autowired
    private AuditoriumService auditoriumService;
/*
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;*/

    protected User user1 = new User();
    protected User user2 = new User();
    protected Event event1 = new Event();
    protected Event event2 = new Event();
    protected Event event3 = new Event();
    protected static Set<Ticket> user1Tickets= new TreeSet<>();
    private static Ticket ticket1 = new Ticket();
    private static Ticket ticket2 = new Ticket();
    private static Ticket ticket3 = new Ticket();
    private static Ticket ticket4 = new Ticket();
    private static LocalDate localDate1 = LocalDate.of(1990, 10, 21);
    private static LocalDate localDate2 = LocalDate.of(1992, 8, 11);
    private static LocalDateTime localDateTime1 = LocalDateTime.of(LocalDate.of(2017, 10, 11),
            LocalTime.of(21, 30, 0));
    protected static LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.of(2017, 10, 14),
            LocalTime.of(20, 0, 0));
    private static LocalDateTime localDateTime3 = LocalDateTime.of(LocalDate.of(2017, 10, 15),
            LocalTime.of(18, 30, 0));
    private static LocalDateTime localDateTime4 = LocalDateTime.of(LocalDate.of(2017, 10, 20),
            LocalTime.of(21, 30, 0));
    private static LocalDateTime localDateTime5 = LocalDateTime.of(LocalDate.of(2017, 10, 20),
            LocalTime.of(0, 30, 0));

    protected static Set<User> users;
    protected static Set<Event> events;
    protected static Set<Ticket> tickets;
    protected static Map<String, Auditorium> auditoriumMap = new HashMap<>();
    protected Set<Auditorium> auditoriums;

    @PostConstruct
    protected void initData() {
        user1.setId(1L);
        user1.setFirstName("Anna");
        user1.setLastName("Malahova");
        user1.setEmail("annaMalah@gmail.com");
        user1.setDateOfBirth(localDate1);

        user2.setId(2L);
        user2.setFirstName("Alexey");
        user2.setLastName("Steblev");
        user2.setEmail("stebelkov@mail.ru");
        user2.setDateOfBirth(localDate2);

        users = new HashSet<>();
        users.add(user1);
        users.add(user2);

        auditoriums = auditoriumService.getAll();
        auditoriumMap = new HashMap<>();
        auditoriums.forEach(a -> auditoriumMap.put(a.getName(), a));


        event1.setName("Bråvalla");
        event1.setBasePrice(1500.0);
        event1.setRating(EventRating.HIGH);
        event1.setAirDates(new TreeSet<>(Arrays.asList(localDateTime1, localDateTime2)));
        NavigableMap<LocalDateTime, Auditorium> map1 = new TreeMap<>();
        map1.put(localDateTime1, auditoriumMap.get("ZoneOne"));
        map1.put(localDateTime2, auditoriumMap.get("ZoneOne"));
        event1.setAuditoriums(map1);

        event2.setName("1+1");
        event2.setBasePrice(400.0);
        event2.setRating(EventRating.MID);
        event2.setAirDates(new TreeSet<>(Arrays.asList(localDateTime3, localDateTime4)));
        NavigableMap<LocalDateTime, Auditorium> map2 = new TreeMap<>();
        map2.put(localDateTime3, auditoriumMap.get("IMax"));
        map2.put(localDateTime4, auditoriumMap.get("ZoneOne"));
        event2.setAuditoriums(map2);

        event3.setName("Elki");
        event3.setBasePrice(300.0);
        event3.setRating(EventRating.LOW);
        event3.setAirDates(new TreeSet<>(Arrays.asList(localDateTime2, localDateTime4, localDateTime5)));
        NavigableMap<LocalDateTime, Auditorium> map3 = new TreeMap<>();
        map3.put(localDateTime2, auditoriumMap.get("IMax"));
        map3.put(localDateTime4, auditoriumMap.get("DolbyDigital"));
        map3.put(localDateTime5, auditoriumMap.get("ZoneOne"));
        event3.setAuditoriums(map3);

        tickets = new HashSet<>();
        for (int i = 40; i < 55; i++) {
            user1Tickets.add(new Ticket(user1, event3, localDateTime2, i));
        }
        user1.setTickets(new TreeSet<>(user1Tickets));
        tickets.addAll(user1Tickets);
    }
}
