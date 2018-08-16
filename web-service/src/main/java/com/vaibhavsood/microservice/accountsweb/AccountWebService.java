package com.vaibhavsood.microservice.accountsweb;

import com.vaibhavsood.microservice.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class AccountWebService {
    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    protected Logger logger = Logger.getLogger(AccountWebService.class
            .getName());

    public AccountWebService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
                : "http://" + serviceUrl;
    }

    public Account findByNumber(String accountNumber) {
        logger.info("findByNumber() invoked: for " + accountNumber);
        return restTemplate.getForObject(serviceUrl + "/accounts/{number}",
                Account.class, accountNumber);
    }
}
