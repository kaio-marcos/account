package com.pingr.accounts.Account.advices;

import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import com.pingr.accounts.Account.exceptions.InvalidFindAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidFindAccountAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidArgumentsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidArgumentsExceptionHandler(InvalidFindAccountException ex){
        return ex.getMessage();
    }
}
