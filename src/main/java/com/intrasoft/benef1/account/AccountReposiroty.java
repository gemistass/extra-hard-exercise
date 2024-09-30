package com.intrasoft.benef1.account;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountReposiroty extends MongoRepository<Account, String> {

    List<Account> findByAccountId(String accountId);
}
