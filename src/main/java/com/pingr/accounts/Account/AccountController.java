package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // C - POST
    // R - GET
    // U - PUT/PATCH
    // D - DELETE

    @PostMapping // POST /api/v1/accounts
    public Account createAccount(@RequestBody Account account) {
        return this.accountService.createAccount(account);
    }

    // query string
    // GET /api/v1/accounts?username=joao
    @GetMapping
    public List<AccountIdAndUsername> searcByUsernameAlike(@RequestParam("usernameAlike") String usernameAlike) {
        return this.accountService.searchByUsernameAlike(usernameAlike);
    }

    @PutMapping // PUT /api/v1/accounts
    public Account updateAccount(@RequestBody Account account){
        return this.accountService.updateAccount(account);
    }

    @DeleteMapping(path = "/{id}") // DELETE /api/v1/accounts/:id
    public Long deleteAccount(@PathVariable("id") Long id){
        return this.accountService.deleteAccount(id);
    }

    @GetMapping(path = "/{id}") // GET /api/v1/accounts/:id
    public Account getAccount(@PathVariable("id") Long id){
        return this.accountService.getAccount(id);
    }
}
