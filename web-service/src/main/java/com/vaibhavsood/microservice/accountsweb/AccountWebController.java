package com.vaibhavsood.microservice.accountsweb;

import com.vaibhavsood.microservice.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class AccountWebController {
    @Autowired
    protected com.vaibhavsood.microservice.accountsweb.AccountWebService accountsService;

    protected Logger logger = Logger.getLogger(AccountWebController.class
            .getName());

    public AccountWebController(com.vaibhavsood.microservice.accountsweb.AccountWebService accountsService) {
        this.accountsService = accountsService;
    }

    @RequestMapping("/accounts")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/accounts/{accountNumber}")
    public String getAccount(Model model,
                           @PathVariable("accountNumber") String accountNumber) {

        logger.info("web-service byNumber() invoked: " + accountNumber);

        com.vaibhavsood.microservice.accounts.Account account = accountsService.findByNumber(accountNumber);
        logger.info("web-service byNumber() found: " + account);
        model.addAttribute("account", account);
        return "account";
    }
}
