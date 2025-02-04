package com.registration.Exception;

public class DuplicateRegistration extends RuntimeException{
    public DuplicateRegistration(String message) {
        super(message);
    }
}
