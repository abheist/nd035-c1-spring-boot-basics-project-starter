package com.udacity.jwdnd.course1.cloudstorage.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidUsernameException extends AuthenticationException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
