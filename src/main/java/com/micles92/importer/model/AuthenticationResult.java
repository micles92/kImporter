package com.micles92.importer.model;

/**
 * Created by mlesniak on 2017-09-14.
 */
public class AuthenticationResult {
    public static final String USER_IS_AUTHENTICATED = "User is authenticated";

    private String message = USER_IS_AUTHENTICATED;
    private boolean valid = true;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
