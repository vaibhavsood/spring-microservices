package com.vaibhavsood.microservice.service;

import com.vaibhavsood.microservice.accountsweb.AccountWebController;
import com.vaibhavsood.microservice.accountsweb.AccountWebService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class WebServer {
    public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";

    public static void main(String[] args) {
        // Will configure using web-server.yml
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(WebServer.class, args);
    }

    @LoadBalanced    // Make sure to create the load-balanced template
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AccountWebService accountsService() {
        return new AccountWebService(ACCOUNTS_SERVICE_URL);
    }

    @Bean
    public AccountWebController accountsController() {
        return new AccountWebController(accountsService());
    }
}
