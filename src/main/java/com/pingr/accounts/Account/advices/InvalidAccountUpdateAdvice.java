package com.pingr.accounts.Account.advices;

import com.pingr.accounts.Account.exceptions.InvalidAccountCreationException;
import com.pingr.accounts.Account.exceptions.InvalidAccountUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidAccountUpdateAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidAccountUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String accountCreationExceptionHandler(InvalidAccountUpdateException ex) {
        return ex.getMessage();
    }
}
