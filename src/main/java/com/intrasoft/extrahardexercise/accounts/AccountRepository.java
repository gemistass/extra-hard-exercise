package com.intrasoft.extrahardexercise.accounts;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, Integer> {

    List<Account> findByAccountId(int accountId);

    List<Account> findByBeneficiaryId(int beneficiaryId);

}
