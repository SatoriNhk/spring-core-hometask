package ua.epam.spring.hometask.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventRepositoryImplTest extends AbstractTest {

    @Autowired
    private EventRepositoryImpl eventRepositoryImpl;

    @Test
    public void saveTestInsert() throws Exception {
        Event expectedEvent = new Event();
        expectedEvent.setName("ElkiTest");
        expectedEvent.setBasePrice(300.00);
        expectedEvent.setRating(EventRating.LOW);
        eventRepositoryImpl.save(expectedEvent);
        Event actualEvent = eventRepositoryImpl.getById(4L);
        assertEquals(expectedEvent,actualEvent);
    }

    @Test
    public void saveTestUpdate() throws Exception {
        Event expectedEvent = new Event();
        expectedEvent.setName("ElkiTest");
        expectedEvent.setBasePrice(300);
        expectedEvent.setRating(EventRating.LOW);
        expectedEvent.setId(3L);
        eventRepositoryImpl.save(expectedEvent);
        Event actualEvent = eventRepositoryImpl.getById(3L);
        assertEquals(expectedEvent,actualEvent);
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void getById() throws Exception {
        Event expectedEvent = new Event();
        expectedEvent.setName("Elki");
        expectedEvent.setBasePrice(400);
        expectedEvent.setRating(EventRating.LOW);
        expectedEvent.setId(3L);
        Event actualEvent = eventRepositoryImpl.getById(3L);
        assertEquals(expectedEvent,actualEvent);
    }

    @Test
    public void getAll() throws Exception {
    }

}