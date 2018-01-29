package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumServiceImpl;

import java.util.Set;

public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AuditoriumServiceImpl service = (AuditoriumServiceImpl) ctx.getBean("auditorium_service");
        for (Auditorium a: service.getAll()
             ) {
            System.out.println(a.getName());
        }
    }
}
