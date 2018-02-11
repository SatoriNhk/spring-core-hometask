package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.counters.EventCounter;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class CounterAspect {

    private EventCounter eventCounter;

    @Autowired
    public CounterAspect(EventCounter eventCounter) {
        this.eventCounter = eventCounter;
    }

    @AfterReturning(pointcut = "execution(* ua.epam.spring.hometask.service.EventService.getByName())",
            returning = "event")
    private void eventGetByNameCounter(Event event) {
        eventCounter.addEventGetByName(event);
    }

    @After("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    private void eventGetTicketsPrice(JoinPoint jp) {
        eventCounter.addEventGetTicketsPrice((Event) jp.getArgs()[0]);
    }

    @Before("execution(* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    private void eventBookedTickets(JoinPoint jp) {
        Ticket[] tickets = (Ticket[]) jp.getArgs()[0];
        List<Event> uniqueEvents = Arrays.stream(tickets).map(Ticket::getEvent).distinct().collect(Collectors.toList());
        for (Event event : uniqueEvents
                ) {
            eventCounter.addEventBookedTickets(event);
        }
    }
}
