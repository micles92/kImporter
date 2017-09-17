package com.micles92.importer.service;

import com.micles92.importer.dao.UserRepository;
import com.micles92.importer.model.AuthenticationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mlesniak on 2017-09-14.
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String INCORRECT_PASSWORD = "Incorrect password";
    @Autowired
    private UserRepository userRepository;


    @Override
    public AuthenticationResult authenticate(String userName, String passwordHash) {

        AuthenticationResult result = new AuthenticationResult();

        if (!userRepository.existsByLogin(userName)) {
            result.setMessage(USER_NOT_FOUND);
            result.setValid(false);
            return result;
        }

        if (!userRepository.existsByLoginAndPassword(userName, passwordHash)) {
            result.setValid(false);
            result.setMessage(INCORRECT_PASSWORD);
            return result;
        }

        return result;
    }
}
