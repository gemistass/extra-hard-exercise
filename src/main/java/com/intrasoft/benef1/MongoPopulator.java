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
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.intrasoft.benef1.account.Account;
import com.intrasoft.benef1.account.AccountReposiroty;
import com.intrasoft.benef1.beneficiaries.Beneficiary;
import com.intrasoft.benef1.beneficiaries.BeneficiaryReposiroty;
import com.intrasoft.benef1.transactions.Transaction;
import com.intrasoft.benef1.transactions.TransactionRepository;

@Configuration
class MongoPopulator {

    private static final Logger log = LoggerFactory.getLogger(MongoPopulator.class);

    @Value("classpath:accounts.csv")
    private Resource accountsResource;

    @Value("classpath:beneficiaries.csv")
    private Resource beneficiariesResource;

    @Value("classpath:transactions.csv")
    private Resource transactionsResource;

    @Bean
    CommandLineRunner initRepos(AccountReposiroty accountRepository, BeneficiaryReposiroty beneficiaryReposiroty,
            TransactionRepository transactionRepository) throws IOException {

        System.out.println("Clearing repositories...");
        clearRepository(accountRepository);
        clearRepository(beneficiaryReposiroty);
        clearRepository(transactionRepository);

        System.out.println("Populating accounts...");
        populateAccounts(accountRepository);
        populateBeneficiaries(beneficiaryReposiroty);
        populateTransactions(transactionRepository);

        return args -> {
            log.info("Accounts populated successfully!");
            log.info("Beneficiaries populated successfully!");
            log.info("Transactions populated successfully!");

        };
    }

    private void populateAccounts(AccountReposiroty repository) throws IOException, FileNotFoundException {
        File file = accountsResource.getFile();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parsedRecord = scanner.nextLine().split(",");
                Account record = new Account(parsedRecord[0], parsedRecord[1]);

                repository.save(record);

            }
        }
    }

    private void populateBeneficiaries(BeneficiaryReposiroty repository) throws IOException, FileNotFoundException {
        File file = beneficiariesResource.getFile();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parsedRecord = scanner.nextLine().split(",");
                // System.err.println(parsedRecord[0] );
                Beneficiary record = new Beneficiary(parsedRecord[0], parsedRecord[1], parsedRecord[2]);
                // System.err.println(record.toString());
                repository.save(record);

            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void populateTransactions(TransactionRepository repository) throws IOException, FileNotFoundException {
        File file = transactionsResource.getFile();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parsedRecord = scanner.nextLine().split(",");
                Transaction record = new Transaction(parsedRecord[0], parsedRecord[1], parsedRecord[2],
                        parsedRecord[3], parsedRecord[4]);

                repository.save(record);

            }
        }
    }

    private <T> void clearRepository(MongoRepository<T, String> repository) {
        repository.deleteAll();
    }

}

@Component
class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Account.class);

    }
}