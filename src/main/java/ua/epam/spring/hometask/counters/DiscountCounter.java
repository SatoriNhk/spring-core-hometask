package ua.epam.spring.hometask.counters;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;

import java.util.Map;

@Component
public class DiscountCounter {

    private Map<User, Long> numberOfBirthdayDiscountsPerUser;
    private Map<User, Long> numberOfTenthTicketDiscountsPerUser;

    public void addBirthdayDiscountPerUser(User user) {
        numberOfBirthdayDiscountsPerUser.merge(user, 1L, Long::sum);
    }

    public void addTenthTicketDiscountPerUser(User user) {
        numberOfTenthTicketDiscountsPerUser.merge(user, 1L, Long::sum);
    }
}
