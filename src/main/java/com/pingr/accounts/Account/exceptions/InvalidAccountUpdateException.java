package com.pingr.accounts.Account.exceptions;

public class InvalidAccountUpdateException extends RuntimeException {
    public InvalidAccountUpdateException(String details) {
        super(details);
    }
}
