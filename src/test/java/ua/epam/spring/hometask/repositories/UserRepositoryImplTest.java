package ua.epam.spring.hometask.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UserRepositoryImplTest extends AbstractTest {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    public void getUserByEmail() throws Exception {
        User expectedUser = new User();
        expectedUser.setFirstName("Helen");
        expectedUser.setLastName("Parker");
        expectedUser.setDateOfBirth(LocalDate.of(1980,10,30));
        expectedUser.setEmail("helen@epam.com");
        expectedUser.setId(3L);
        User actualUser = userRepositoryImpl.getUserByEmail("helen@epam.com");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void saveTestInsert() throws Exception {
        User expectedUser = new User();
        expectedUser.setFirstName("TestUserName");
        expectedUser.setLastName("TestUserSurName");
        expectedUser.setDateOfBirth(LocalDate.of(1980,10,30));
        expectedUser.setEmail("test@epam.com");
        userRepositoryImpl.save(expectedUser);
        User savedUser = userRepositoryImpl.getById(4L);
        assertEquals(expectedUser, savedUser);
    }

    @Test
    public void saveTestUpdate() throws Exception {
        User expectedUser = new User();
        expectedUser.setFirstName("HelenTest");
        expectedUser.setLastName("Parker");
        expectedUser.setDateOfBirth(LocalDate.of(1980,10,30));
        expectedUser.setEmail("helen@epam.com");
        expectedUser.setId(3L);
        userRepositoryImpl.save(expectedUser);
        User savedUser = userRepositoryImpl.getById(3L);
        assertEquals(expectedUser, savedUser);
    }

    @Test
    public void remove() throws Exception {
    }

    @Test
    public void getById() throws Exception {
        User expectedUser = new User();
        expectedUser.setFirstName("Helen");
        expectedUser.setLastName("Parker");
        expectedUser.setDateOfBirth(LocalDate.of(1980,10,30));
        expectedUser.setEmail("helen@epam.com");
        expectedUser.setId(3L);
        User actualUser = userRepositoryImpl.getById(3L);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getAll() throws Exception {
    }

}