package com.pingr.accounts.Account;

import com.pingr.accounts.Account.exceptions.InvalidAccountCreationException;
import com.pingr.accounts.Account.exceptions.InvalidAccountUpdateException;
import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import com.pingr.accounts.Account.exceptions.InvalidFindAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ProducerService producerService;

    @Autowired
    public AccountService(
            AccountRepository accountRepository,
            ProducerService producerService
    ) {
        this.accountRepository = accountRepository;
        this.producerService = producerService;
    }

    public Account createAccount(Account account) {
        if (account == null) throw new InvalidAccountCreationException("conta não pode ser nula");

        try {
            Account savedAccount = this.accountRepository.save(account);
            this.producerService.sendMessage(savedAccount);
            return savedAccount;
        } catch (Exception e) {
            throw new InvalidAccountCreationException("conta inválida para criação");
        }
    }

    public List<AccountIdAndUsername> searchByUsernameAlike(String usernameAlike) {
        if (usernameAlike.length() == 0) throw new InvalidArgumentsException("termo de busca vazio");

        return this.accountRepository.searchByUsernameAlike(usernameAlike);
    }

    public Account updateAccount(Account account){
        if (account == null || account.getId() == null)
            throw new InvalidArgumentsException("conta ou id não podem ser nulos");

        try {
            Optional<Account> accountFind = accountRepository.findById(account.getId());
            if(!accountFind.isPresent())
                throw new InvalidFindAccountException(account);

            Account accountUpdate = accountFind.get();
            if(account.getEmail() != null)
                accountUpdate.setEmail(account.getEmail());
            if(account.getUsername() != null)
                accountUpdate.setUsername(account.getUsername());
            if(account.getPassword() != null)
                accountUpdate.setPassword(account.getPassword());
            accountRepository.save(accountUpdate);
            return accountUpdate;
        }catch (Exception ex){
            throw new InvalidAccountUpdateException("Falha ao atualizar 'Account' ");
        }
    }

    public Long deleteAccount(Long id){
        if (id == null)
            throw new InvalidArgumentsException("id não pode ser nulo");

        try {
            Optional<Account> accountFind = accountRepository.findById(id);
            if(!accountFind.isPresent())
                throw new InvalidFindAccountException(new Account(id, "invalid", "invalid", "invalid"));

            Account account = accountFind.get();
            accountRepository.delete(account);
            return id;
        }catch (Exception ex){
            throw new InvalidAccountUpdateException("Falha ao deletar 'Account' ");
        }
    }

    public Account getAccount(Long id){
        if (id == null)
            throw new InvalidArgumentsException("id não pode ser nulo");

        try {
            Optional<Account> accountFind = accountRepository.findById(id);
            if(!accountFind.isPresent())
                throw new InvalidFindAccountException(new Account(id, "invalid", "invalid", "invalid"));

            Account account = accountFind.get();
            account.setPassword("");
            return account;
        }catch (Exception ex){
            throw new InvalidAccountUpdateException("Falha ao encontrar uma 'Account' ");
        }
    }
}
