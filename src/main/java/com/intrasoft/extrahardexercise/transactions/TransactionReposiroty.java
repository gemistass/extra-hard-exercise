package com.intrasoft.extrahardexercise.transactions;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionReposiroty extends MongoRepository<Transaction, String> {

    List<Transaction> findByTransactionId(String transactionId);

}
