package ua.epam.spring.hometask.service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.AbstractTest;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repositories.UserRepository;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest extends AbstractTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Before
    public void initMockito() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByEmail() throws Exception {
        when(userRepository.getAll()).thenReturn(users);
        User actualUser = userServiceImpl.getUserByEmail("annaMalah@gmail.com");
        assertEquals(user1,actualUser);
    }

    @Test
    public void getById() throws Exception {
        when(userRepository.getAll()).thenReturn(users);
        User actualUser = userServiceImpl.getById(2L);
        assertEquals(user2,actualUser);
    }
}