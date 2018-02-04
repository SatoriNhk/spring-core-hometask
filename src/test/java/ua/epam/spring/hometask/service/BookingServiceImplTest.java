package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class BookingServiceImplTest extends AbstractTest {

    @Mock
    private Auditorium auditorium;

    @Mock
    private DiscountService discountService;

    @Mock
    private Event event;

    @Mock
    private NavigableMap<LocalDateTime, Auditorium> test = new TreeMap<>();

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    @Autowired
    private BookingService bookingService;

    @Before
    public void initMock() {
        MockitoAnnotations.initMocks(this);
        initData();
    }

    @Test
    public void getTicketsPrice() throws Exception {
        User user = user1;// USER
        LocalDateTime data = LocalDateTime.of(1900, 12, 12, 2, 2);
        Set<Long> seats = new TreeSet<>(Arrays.asList(10L, 11L, 12L, 13L, 14L));//SEAT

        when(event.getAuditoriums()).thenReturn(test);
        //when(event.getAuditoriums().get(data)).thenReturn(auditorium);
        when(test.get(data)).thenReturn(auditorium);
        when(auditorium.countVipSeats(seats)).thenReturn(0L);//VIP seat count
        when(event.getBasePrice()).thenReturn(100d);//PRICE for ONE ticket
        when(event.getRating()).thenReturn(EventRating.MID);//EVENT RATING
        when(discountService.getDiscount(user, event, data, seats.size())).thenReturn((byte) 5);//DISCOUNT
        double actualValue = bookingService.getTicketsPrice(event, data, user, seats);
        System.out.println(actualValue);
    }

    @Test
    public void bookTickets() throws Exception {
    }


    @Test
    public void getPurchasedTicketsForEvent() throws Exception {
        //LocalDateTime data = LocalDateTime.of(1900,12,12,2,2);
        /*Ticket TicketOne = new Ticket(user1,event,data,42);
        Ticket TicketTwo = new Ticket(user1,event,data,43);
        Ticket TicketThree = new Ticket(user1,event2,data,44);
        */
        //Set<Ticket> TicketList = new TreeSet<>(Arrays.asList(TicketOne,TicketTwo,TicketThree));
        when(ticketRepository.getAll()).thenReturn(tickets);
        Set<Ticket> Result = bookingService.getPurchasedTicketsForEvent(event3, localDateTime2);
        System.out.println("Base ticket = " + tickets);
        System.out.println("Result = " + Result);
    }

}