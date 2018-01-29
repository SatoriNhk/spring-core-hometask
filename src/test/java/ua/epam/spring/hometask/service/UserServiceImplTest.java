package ua.epam.spring.hometask.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest extends AbstractTest {

    @Autowired
    UserService userService;

    @Before
    public void init() throws Exception {

    }

}