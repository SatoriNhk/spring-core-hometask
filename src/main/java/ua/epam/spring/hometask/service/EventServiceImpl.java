package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.repositories.EventRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventRepository.getAll()
                .stream()
                .filter(event -> name.equals(event.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event save(@Nonnull Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventRepository.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventRepository.getAll()
                .stream()
                .filter(event -> id.equals(event.getId()))
                .findFirst()
                .orElse(null);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventRepository.getAll();
    }
}
