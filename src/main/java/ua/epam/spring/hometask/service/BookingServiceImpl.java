package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repositories.TicketRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private TicketRepository ticketRepository;

    @Autowired
    public BookingServiceImpl(DiscountService discountService, TicketRepository ticketRepository) {
        this.discountService = discountService;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {

        Auditorium auditoriumForGivenEventAndDateTime = event.getAuditoriums().get(dateTime);
        long totalAmountOfSeats = seats.size();

        long amountOfVipSeats = auditoriumForGivenEventAndDateTime.countVipSeats(seats);

        double ticketPriceBasedOnEventRating;

        switch (event.getRating()) {
            case LOW:
                ticketPriceBasedOnEventRating = event.getBasePrice() * 0.8;
                break;
            case HIGH:
                ticketPriceBasedOnEventRating = event.getBasePrice() * 1.2;
                break;
            default:
                ticketPriceBasedOnEventRating = event.getBasePrice();
        }

        double price = ticketPriceBasedOnEventRating * (2 * amountOfVipSeats + (totalAmountOfSeats - amountOfVipSeats));

        byte discount = discountService.getDiscount(user, event, dateTime, seats.size());

        return price * (1 - ((double) discount / 100));
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            if (ticket.getUser() != null) {
                ticketRepository.save(ticket);
                ticket.getUser().getTickets().addAll(tickets);
            } else {
                ticketRepository.save(ticket);
            }
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {

        return ticketRepository.getAll()
                .stream()
                .filter(ticket -> ticket.getEvent().equals(event))
                .filter(ticket -> ticket.getDateTime().equals(dateTime))
                .collect(Collectors.toSet());

    }
}
