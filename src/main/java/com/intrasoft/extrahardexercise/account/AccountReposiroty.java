package com.intrasoft.extrahardexercise.account;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountReposiroty extends MongoRepository<Account, String> {

    List<Account> findByAccountId(String accountId);
}
