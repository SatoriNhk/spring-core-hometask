package ua.epam.spring.hometask.counters;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;

import java.util.Map;

@Component
public class EventCounter {
    private static Map<Event, Long> eventGetByNameCounter;
    private static Map<Event, Long> eventBookedTicketsCounter;
    private static Map<Event, Long> eventGetTicketsPriceCounter;

    public void addEventGetByName(Event event) {
        eventGetByNameCounter.merge(event, 1L, Long::sum);
    }

    public void addEventBookedTickets(Event event) {
        eventBookedTicketsCounter.merge(event, 1L, Long::sum);
    }

    public void addEventGetTicketsPrice(Event event) {
        eventGetTicketsPriceCounter.merge(event, 1L, Long::sum);
    }
}
