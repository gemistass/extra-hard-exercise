package com.intrasoft.extrahardexercise.transactions;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intrasoft.extrahardexercise.account.Account;
import com.intrasoft.extrahardexercise.account.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/transactions")
@Slf4j
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/{id}")
    Transaction one(@PathVariable int id) {

        return transactionRepository.findById(id).get();

    }

    @GetMapping("")
    public List<Transaction> findTransactionsByBeneficiaryId(@RequestParam int beneficiaryId) {
        List<Account> accounts = accountRepository.findByBeneficiaryId(beneficiaryId);
        List<Transaction> transactions = new LinkedList<Transaction>();

        accounts.forEach((account) -> {
            int id = account.getAccountId();
            transactions.addAll(transactionRepository.findByAccountId(id));
        });

         return transactions;
    }
}
