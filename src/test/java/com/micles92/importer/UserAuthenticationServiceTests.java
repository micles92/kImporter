package com.micles92.importer;

import com.micles92.importer.dao.UserRepository;
import com.micles92.importer.model.AuthenticationResult;
import com.micles92.importer.model.entity.User;
import com.micles92.importer.service.UserAuthenticationService;
import com.micles92.importer.service.UserAuthenticationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.micles92.importer.model.AuthenticationResult.USER_IS_AUTHENTICATED;
import static com.micles92.importer.service.UserAuthenticationServiceImpl.INCORRECT_PASSWORD;
import static com.micles92.importer.service.UserAuthenticationServiceImpl.USER_NOT_FOUND;

/**
 * Created by mlesniak on 2017-09-14.
 */
@RunWith(SpringRunner.class)
public class UserAuthenticationServiceTests {

    @TestConfiguration
    static class UserAuthenticationServiceTestContextConfiguration {

        @Bean
        public UserAuthenticationService userAuthenticationService() {
            return new UserAuthenticationServiceImpl();
        }
    }


    @MockBean
    private UserRepository userRepository;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Before
    public void initTest() {
        User user = new User("admin", "password", "admin@gmail.com", "USER");
        Mockito.when(userRepository.findByLogin(user.getLogin())).thenReturn(user);
        Mockito.when(userRepository.existsByLogin((user.getLogin()))).thenReturn(true);
        Mockito.when(userRepository.existsByLoginAndPassword((user.getLogin()),user.getPassword())).thenReturn(true);



    }

    @Test
    public void testAuthenticate() {
        AuthenticationResult result = userAuthenticationService.authenticate("admin", "password");

        Assert.assertTrue(result.isValid());
        Assert.assertEquals(USER_IS_AUTHENTICATED, result.getMessage());
    }

    @Test
    public void testAuthenticateWithInvalidCredentials() {
        AuthenticationResult result = userAuthenticationService.authenticate("xxxxx", "xxsxs");

        Assert.assertFalse(result.isValid());
        Assert.assertEquals(USER_NOT_FOUND, result.getMessage());
    }

    @Test
    public void testAuthenticateWithIncorrectPassword() {
        AuthenticationResult result = userAuthenticationService.authenticate("admin", "WRONG_PASSWORD");

        Assert.assertFalse(result.isValid());
        Assert.assertEquals(INCORRECT_PASSWORD, result.getMessage());
    }
}
