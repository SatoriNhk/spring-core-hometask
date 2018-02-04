package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.AuditoriumServiceImpl;
import ua.epam.spring.hometask.service.DiscountServiceImpl;
import ua.epam.spring.hometask.service.UserServiceImpl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        //ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DiscountServiceImpl discountService = ctx.getBean(DiscountServiceImpl.class);
        System.out.println(Arrays.toString(discountService.getDiscountStrategyList().toArray()));

        AuditoriumServiceImpl auditoriumService = ctx.getBean(AuditoriumServiceImpl.class);
        System.out.println(Arrays.toString(auditoriumService.getAll().toArray()));

        Map<String, Auditorium> beansOfType = ctx.getBeansOfType(Auditorium.class);
        for (Map.Entry<String, Auditorium> entry: beansOfType.entrySet()
             ) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getName());
        }


    }
}
