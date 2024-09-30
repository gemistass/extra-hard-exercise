package com.intrasoft.benef1.transactions;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    List<Transaction> findByTransactionId(String transactionId);

}
