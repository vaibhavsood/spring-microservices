package com.vaibhavsood.microservice.accounts;

import org.springframework.data.repository.Repository;

public interface AccountRepository extends Repository<com.vaibhavsood.microservice.accounts.Account, Long> {
    public com.vaibhavsood.microservice.accounts.Account findByNumber(String accountNumber);
}
