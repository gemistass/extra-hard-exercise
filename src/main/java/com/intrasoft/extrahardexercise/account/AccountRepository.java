package com.intrasoft.extrahardexercise.account;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, Integer> {

    List<Account> findByAccountId(int accountId);

    List<Account> findByBeneficiaryId(int beneficiaryId);

}
