package com.vaibhavsood.microservice.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AccountController {
    private com.vaibhavsood.microservice.accounts.AccountRepository accountRepository;
    private Logger logger = Logger.getLogger(AccountController.class.getName());

    @Autowired
    public  AccountController(com.vaibhavsood.microservice.accounts.AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping("/accounts/{accountNumber}")
    public com.vaibhavsood.microservice.accounts.Account getAccount(@PathVariable("accountNumber") String accountNumber) {
        logger.info("getAccount invoked with number:" + accountNumber);
        return accountRepository.findByNumber(accountNumber);
    }
}
