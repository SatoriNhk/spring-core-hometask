package ua.epam.spring.hometask.counters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.repositories.EventCounterRepository;

@Component
public class EventCounter {

    private EventCounterRepository eventCounterRepository;

    @Autowired
    public EventCounter(EventCounterRepository eventCounterRepository) {
        this.eventCounterRepository = eventCounterRepository;
    }

    public void addEventGetByName(Event event) {
        Long currentCount = eventCounterRepository.getCallingsByName(event);
        if (currentCount != null) {
            eventCounterRepository.putCallingsByName(event, currentCount + 1);
        } else {
            eventCounterRepository.putCallingsByName(event, 1L);
        }
    }

    public void addEventBookedTickets(Event event) {
        Long currentCount = eventCounterRepository.getCallingsByBooking(event);
        if (currentCount != null) {
            eventCounterRepository.putCallingsByBooking(event, currentCount + 1);
        } else {
            eventCounterRepository.putCallingsByBooking(event, 1L);
        }
    }

    public void addEventGetTicketsPrice(Event event) {
        Long currentCount = eventCounterRepository.getCallingsByPrice(event);
        if (currentCount != null) {
            eventCounterRepository.putCallingsByPrice(event, currentCount + 1);
        } else {
            eventCounterRepository.putCallingsByPrice(event, 1L);
        }
    }
}
