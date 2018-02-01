package ua.epam.spring.hometask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategyService> discountStrategyList;

    @Autowired
    public DiscountServiceImpl(List<DiscountStrategyService> discountStrategyList) {
        this.discountStrategyList = discountStrategyList;
    }

    public List<DiscountStrategyService> getDiscountStrategyList() {
        return discountStrategyList;
    }

    public void setDiscountStrategyList(List<DiscountStrategyService> discountStrategyList) {
        this.discountStrategyList = discountStrategyList;
    }


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte totalDiscount = 0;
        for (DiscountStrategyService discountStrategy : discountStrategyList) {
            totalDiscount = (byte) Math.max(discountStrategy.getDiscount(user, event, airDateTime, numberOfTickets), totalDiscount);
        }
        return totalDiscount;
    }
}
