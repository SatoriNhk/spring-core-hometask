package ua.epam.spring.hometask.repositories;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import java.util.List;


public interface TicketRepository extends AbstractRepository<Ticket> {

    List<Ticket> getByUser(@Nonnull User user);
}
