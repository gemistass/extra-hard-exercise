package com.intrasoft.extrahardexercise.transactions;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, Integer> {

    List<Transaction> findByTransactionId(String transactionId);

}
