package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service
@PropertySource("classpath:properties/discounts.properties")
public class DiscountStrategyServiceTenthTicketImpl implements DiscountStrategyService {

    @Value("${basic.tenth.ticket.discount}")
    private String basicDiscount;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long tickets = user != null? user.getTickets().size() % 10 + numberOfTickets : numberOfTickets;
        long discount = tickets / 10 * Integer.valueOf(basicDiscount);
        return (byte) (discount <= 50? discount : 50);
    }
}
