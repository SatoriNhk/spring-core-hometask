package ua.epam.spring.hometask.counters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repositories.BirthdayDiscountCounterRepository;
import ua.epam.spring.hometask.repositories.TenthTicketDiscountCounterRepository;

@Component
public class DiscountCounter {

    private BirthdayDiscountCounterRepository birthdayDiscountCounterRepository;

    @Autowired
    public DiscountCounter(BirthdayDiscountCounterRepository birthdayDiscountCounterRepository,
                           TenthTicketDiscountCounterRepository tenthTicketDiscountCounterRepository) {
        this.birthdayDiscountCounterRepository = birthdayDiscountCounterRepository;
        this.tenthTicketDiscountCounterRepository = tenthTicketDiscountCounterRepository;
    }

    private TenthTicketDiscountCounterRepository tenthTicketDiscountCounterRepository;

    public void addBirthdayDiscountPerUser(User user) {
        Long currentCount = birthdayDiscountCounterRepository.getDiscountsNumberByUser(user);
        if (currentCount != null) {
            birthdayDiscountCounterRepository.save(user, currentCount + 1);
        }
        else {
            birthdayDiscountCounterRepository.save(user, 1L);
        }
    }

    public void addTenthTicketDiscountPerUser(User user) {
        Long currentCount = tenthTicketDiscountCounterRepository.getDiscountsNumberByUser(user);
        if (currentCount != null) {
            tenthTicketDiscountCounterRepository.save(user, currentCount + 1);
        }
        else {
            tenthTicketDiscountCounterRepository.save(user, 1L);
        }
    }
}
