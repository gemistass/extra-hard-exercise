package com.intrasoft.benef1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.intrasoft.benef1.accountHandler.Account;
import com.intrasoft.benef1.accountHandler.AccountReposiroty;

@Configuration
// @SuppressWarnings({ "all" })
class MongoPopulator {

    private static final Logger log = LoggerFactory.getLogger(MongoPopulator.class);

    @Value("classpath:accounts.csv")
    private Resource accountsResource;

    @Value("classpath:beneficiaries.csv")
    private Resource beneficiariesResource;

    @Value("classpath:accounts.csv")
    private Resource transactionsResource;

    @Bean
    CommandLineRunner initRepos(AccountReposiroty accountRepository) throws IOException {

        clearRepositories(accountRepository);

        System.err.println("Populating accounts...");
        populate(accountRepository);

        return args -> {
            log.info("Accounts populated successfully!");

        };
    }

    private void populate(AccountReposiroty repository) throws IOException, FileNotFoundException {
        File file = accountsResource.getFile();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parsedRecord = scanner.nextLine().split(",");
                Account record = new Account(parsedRecord[0], parsedRecord[1]);

                repository.save(record);

            }
        }
    }

    private void clearRepositories(AccountReposiroty accountRepository) {
        System.err.println("Clearing repositories...");
        accountRepository.deleteAll();
    }

}

@Component
class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Account.class);

    }

}