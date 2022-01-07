package com.pingr.accounts.Account.exceptions;

import com.pingr.accounts.Account.Account;

public class InvalidFindAccountException extends RuntimeException{
    public InvalidFindAccountException(Account account){
        super("Não foi possível encontrar uma 'Account' a partir deste objeto" + account);
    }
}
