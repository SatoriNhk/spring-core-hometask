package ua.epam.spring.hometask.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TicketRepositoryImplTest extends AbstractTest {

    @Autowired
    private TicketRepositoryImpl ticketRepositoryImpl;

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private EventRepositoryImpl eventRepositoryImpl;

    @Test
    public void getByUser() throws Exception {
        User testDataUser = userRepositoryImpl.getById(3L);
        ArrayList<Ticket> expectedTicket = (ArrayList<Ticket>) ticketRepositoryImpl.getByUser(testDataUser);
        System.out.println(expectedTicket);
    }

    @Test
    public void saveTestInsert() throws Exception {
        User testDataUser = userRepositoryImpl.getById(1L);
        Event testDataEvent = eventRepositoryImpl.getById(3L);

        Ticket expectedTicket = new Ticket();
        expectedTicket.setUser(testDataUser);
        expectedTicket.setSeat(40);
        expectedTicket.setEvent(testDataEvent);
        expectedTicket.setDateTime(LocalDateTime.of(2017,10,14,00,0,0));
        ticketRepositoryImpl.save(expectedTicket);
        expectedTicket.setId(16L);
        Ticket actualTicket = ticketRepositoryImpl.getById(16L);
        assertEquals(expectedTicket.getUser().getId(), actualTicket.getUser().getId());
        assertEquals(expectedTicket.getSeat(), actualTicket.getSeat());
        assertEquals(expectedTicket.getId(), actualTicket.getId());
    }

    @Test
    public void saveTestUpdate() throws Exception {
        User testDataUser = userRepositoryImpl.getById(1L);
        Event testDataEvent = eventRepositoryImpl.getById(3L);

        Ticket expectedTicket = new Ticket();
        expectedTicket.setId(5L);
        expectedTicket.setUser(testDataUser);
        expectedTicket.setSeat(50);
        expectedTicket.setEvent(testDataEvent);
        expectedTicket.setDateTime(LocalDateTime.of(2017,10,14,00,0,0));
        ticketRepositoryImpl.save(expectedTicket);
        Ticket actualTicket = ticketRepositoryImpl.getById(5L);
        assertEquals(expectedTicket.getUser().getId(), actualTicket.getUser().getId());
        assertEquals(expectedTicket.getSeat(), actualTicket.getSeat());
        assertEquals(expectedTicket.getId(), actualTicket.getId());
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void getById() throws Exception {
        User testDataUser = new User();
        testDataUser.setFirstName("Helen");
        testDataUser.setLastName("Parker");
        testDataUser.setDateOfBirth(LocalDate.of(1980,10,30));
        testDataUser.setEmail("helen@epam.com");
        testDataUser.setId(3L);

        Event testDataEvent = new Event();
        testDataEvent.setName("Elki");
        testDataEvent.setBasePrice(300);
        testDataEvent.setRating(EventRating.LOW);

        Ticket expectedTicket = new Ticket();
        expectedTicket.setUser(testDataUser);
        expectedTicket.setSeat(40);
        expectedTicket.setEvent(testDataEvent);
        expectedTicket.setDateTime(LocalDateTime.of(2017,10,14,00,0,0));
        Ticket actualTicket = ticketRepositoryImpl.getById(1L);
        assertEquals(expectedTicket,actualTicket);
    }

    @Test
    public void getAll() throws Exception {
    }

}