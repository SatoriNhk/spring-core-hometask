package ua.epam.spring.hometask.service;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
public class DiscountStrategyServiceTenthTicketImpl implements DiscountStrategyService {
    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long tickets = user != null? user.getTickets().size() % 10 + numberOfTickets : numberOfTickets;
        long discount = tickets / 10 * 5;
        return (byte) (discount <= 50? discount : 50);
    }
}
