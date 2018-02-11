package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
import ua.epam.spring.hometask.domain.Auditorium;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuditoriumServiceImplTest extends AbstractTest {

    @Autowired
    private AuditoriumServiceImpl auditoriumServiceImpl;

    @Test
    public void getByName() throws Exception {
        Auditorium actualAuditorium = auditoriumServiceImpl.getByName("ZoneOne");
        assertEquals(auditoriumMap.get("ZoneOne"), actualAuditorium);
    }

    @Before
    public void init() throws Exception {
    }

}