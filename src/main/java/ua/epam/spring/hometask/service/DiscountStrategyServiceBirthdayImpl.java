package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@PropertySource("classpath:properties/discounts.properties")
public class DiscountStrategyServiceBirthdayImpl implements DiscountStrategyService{

    @Value("${basic.tenth.ticket.discount}")
    private String discount;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime dateTime, long numberOfTickets) {

        if (user != null && Math.abs(ChronoUnit.DAYS.between(user.getDateOfBirth(), dateTime) % 365) <= 5) {
            return Byte.valueOf(discount);
        }
        return 0;
    }
}
