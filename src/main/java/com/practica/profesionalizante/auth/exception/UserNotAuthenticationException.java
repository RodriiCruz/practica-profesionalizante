package com.practica.profesionalizante.auth.exception;

public class UserNotAuthenticationException extends RuntimeException{
    public UserNotAuthenticationException(String message) {
        super(message);
    }
}
