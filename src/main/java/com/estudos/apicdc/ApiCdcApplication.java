package com.estudos.apicdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class ApiCdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCdcApplication.class, args);
    }

}
