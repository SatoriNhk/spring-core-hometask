package ua.epam.spring.hometask.counters;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventCounter {
    private Map<Event, Long> eventGetByNameCounter;
    private Map<Event, Long> eventBookedTicketsCounter;
    private Map<Event, Long> eventGetTicketsPriceCounter;

    public EventCounter() {
        eventGetByNameCounter = new HashMap<>();
        eventBookedTicketsCounter = new HashMap<>();
        eventGetTicketsPriceCounter = new HashMap<>();
    }

    public void addEventGetByName(Event event) {
        eventGetByNameCounter.merge(event, 1L, Long::sum);
    }

    public void addEventBookedTickets(Event event) {
        eventBookedTicketsCounter.merge(event, 1L, Long::sum);
    }

    public void addEventGetTicketsPrice(Event event) {
        eventGetTicketsPriceCounter.merge(event, 1L, Long::sum);
    }

    public Map<Event, Long> getEventGetByNameCounter() {
        return eventGetByNameCounter;
    }

    public Map<Event, Long> getEventBookedTicketsCounter() {
        return eventBookedTicketsCounter;
    }

    public Map<Event, Long> getEventGetTicketsPriceCounter() {
        return eventGetTicketsPriceCounter;
    }
}
