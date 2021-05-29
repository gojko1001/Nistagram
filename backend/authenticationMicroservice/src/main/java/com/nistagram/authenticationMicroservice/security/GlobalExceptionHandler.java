package com.nistagram.authenticationMicroservice.security;

import com.nistagram.authenticationMicroservice.exception.UnauthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public UnauthorizeException handleAuthenticationException(AuthenticationException ex, HttpServletResponse response) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return new UnauthorizeException(ex.getMessage());
    }
}
