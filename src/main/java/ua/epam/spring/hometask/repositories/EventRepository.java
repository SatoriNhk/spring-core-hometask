package ua.epam.spring.hometask.repositories;

import ua.epam.spring.hometask.domain.Event;

import java.util.HashSet;
import java.util.Set;

public class EventRepository {

    private static Set<Event> events = new HashSet<>();

    public Set<Event> getAll() {
        return events;
    }

    public void remove(Event auditorium){
        events.remove(auditorium);
    }

    public void save(Event auditorium){
        events.add(auditorium);
    }
}
