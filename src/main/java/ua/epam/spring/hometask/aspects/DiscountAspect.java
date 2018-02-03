package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.counters.DiscountCounter;
import ua.epam.spring.hometask.domain.User;

@Aspect
@Component
public class DiscountAspect {

    private DiscountCounter discountCounter;

    @Autowired
    public DiscountAspect(DiscountCounter discountCounter) {
        this.discountCounter = discountCounter;
    }


    @After("execution(*  ua.epam.spring.hometask.service.DiscountStrategyServiceTenthTicketImpl.getDiscount())")
    private void countTenthTicketDiscountsPerUser(JoinPoint jp) throws Exception {
        discountCounter.addTenthTicketDiscountPerUser((User) jp.getArgs()[0]);
    }


    @After("execution(*  ua.epam.spring.hometask.service.DiscountStrategyServiceBirthdayImpl.getDiscount())")
    private void countBirthdayDiscountsPerUser(JoinPoint jp) throws Exception {
        discountCounter.addBirthdayDiscountPerUser((User) jp.getArgs()[0]);
    }

    /*@AfterReturning(pointcut = "execution(*  ua.epam.spring.hometask.service.DiscountServiceImpl.getDiscount())",
            returning = "retDiscount")
    private void countBirthdayDiscountsCalling(JoinPoint jp, Object retDiscount) throws Exception {
        if ((byte) retDiscount == birthdayDiscount.getDiscount(
                (User) jp.getArgs()[0],
                (Event) jp.getArgs()[1],
                (LocalDateTime) jp.getArgs()[2],
                (Long) jp.getArgs()[3])) {
            discountCounter.addBirthdayDiscountPerUser((User) jp.getArgs()[0]);
        }

        if ((byte) retDiscount == tenthTicketDiscount.getDiscount(
                (User) jp.getArgs()[0],
                (Event) jp.getArgs()[1],
                (LocalDateTime) jp.getArgs()[2],
                (Long) jp.getArgs()[3])) {
            discountCounter.addTenthTicketDiscountPerUser((User) jp.getArgs()[0]);
        }
    }*/

}
