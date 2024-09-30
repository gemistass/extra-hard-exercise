package com.intrasoft.benef1.accountHandler;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountReposiroty extends MongoRepository<Account, String> {

    List<Account> findByAccountId(String accountId);
}
