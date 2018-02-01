package ua.epam.spring.hometask.repositories;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashSet;
import java.util.Set;

@Component
public class TicketRepository {

    private static Set<Ticket> tickets = new HashSet<>();

    public Set<Ticket> getAll() {
        return tickets;
    }

    public void remove(Ticket ticket){
        tickets.remove(ticket);
    }

    public void save(Ticket ticket){
        tickets.add(ticket);
    }
}
