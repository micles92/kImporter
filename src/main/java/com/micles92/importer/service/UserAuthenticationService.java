package com.micles92.importer.service;

import com.micles92.importer.model.AuthenticationResult;

/**
 * Created by mlesniak on 2017-09-14.
 */

public interface UserAuthenticationService {

    AuthenticationResult authenticate(String userName, String passwordHash);
}
