package com.intrasoft.extrahardexercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.intrasoft.extrahardexercise.account.Account;
import com.intrasoft.extrahardexercise.account.AccountRepository;
import com.intrasoft.extrahardexercise.beneficiaries.Beneficiary;
import com.intrasoft.extrahardexercise.beneficiaries.BeneficiaryRepository;
import com.intrasoft.extrahardexercise.transactions.Transaction;
import com.intrasoft.extrahardexercise.transactions.TransactionRepository;

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
    CommandLineRunner initRepos(AccountRepository accountRepository, BeneficiaryRepository beneficiaryReposiroty,
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

    private void populateAccounts(AccountRepository repository) throws IOException, FileNotFoundException {
        File file = accountsResource.getFile();
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String[] parsedRecord = scanner.nextLine().split(",");

                try {
                    Account record = new Account(Integer.parseInt(parsedRecord[0]), Integer.parseInt(parsedRecord[1]));
                    repository.save(record);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
    }

    private void populateBeneficiaries(BeneficiaryRepository repository) throws IOException, FileNotFoundException {
        File file = beneficiariesResource.getFile();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parsedRecord = scanner.nextLine().split(",");

                try {
                    Beneficiary record = new Beneficiary(Integer.parseInt(parsedRecord[0]),
                            parsedRecord[1],
                            parsedRecord[2]);
                    repository.save(record);
                } catch (NumberFormatException e) {
                    continue;
                }

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

                try {

                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
                    Date date = formatter.parse(parsedRecord[4]);

                    Transaction record = new Transaction(Integer.parseInt(parsedRecord[0]),
                            Integer.parseInt(parsedRecord[1]),
                            Float.parseFloat(parsedRecord[2]),
                            parsedRecord[3], date);

                    repository.save(record);
                } catch (NumberFormatException e) {
                    System.err.println("Unhandled record");
                    e.printStackTrace();
                    continue;
                } catch (ParseException e) {
                    System.err.println("Unhandled record");
                    e.printStackTrace();
                    continue;
                }

            }
        }
    }

    private <T> void clearRepository(MongoRepository<T, Integer> repository) {
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