package com.intrasoft.extrahardexercise.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transactions/{id}")
    Transaction one(@PathVariable int id) {

        return transactionRepository.findById(id).get();

    }
}
