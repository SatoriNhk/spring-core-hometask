package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class DiscountServiceImplTest extends AbstractTest {

    @Autowired
    private DiscountServiceImpl discountServiceImpl;

    @Test
    public void getDiscount1() throws Exception {
    }

    @Before
    public void init() throws Exception {
    }

    @Test
    public void getDiscountTenthTicket() throws Exception {
        byte actualDiscount = discountServiceImpl.getDiscount(user2, event1, LocalDateTime.now(), 15);
        assertEquals(5, actualDiscount);
    }

    @Test
    public void getDiscountBirthDay() throws Exception {
        byte actualDiscount = discountServiceImpl.getDiscount(user2, event1, LocalDateTime.of(1994, 8, 11, 14, 21), 2);
        assertEquals(5, actualDiscount);
    }

    @Test
    public void getDiscountBirthDayWithTenthTicket() throws Exception {
        byte actualDiscount = discountServiceImpl.getDiscount(user2, event1, LocalDateTime.of(1992, 8, 11, 14, 21), 20);
        assertEquals(10, actualDiscount);
    }
}