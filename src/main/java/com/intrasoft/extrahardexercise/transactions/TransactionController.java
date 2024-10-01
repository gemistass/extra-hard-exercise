package com.intrasoft.extrahardexercise.transactions;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

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
    private long MONTH_DURATION_IN_MILLIS = 2592000000L;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionController transactionController;

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

    @GetMapping("/lastMonth")
    Double findBiggestDepositByBeneficiaryId(@RequestParam int beneficiaryId) {

        // TransactionController transactionController = new TransactionController();
        // //TODO remove circular dependency
        System.err.println("-------------------------------------------------------------");

        List<Transaction> transactions = transactionController
                .findTransactionsByBeneficiaryId(beneficiaryId);
        transactions.stream().map(transaction -> {
            System.err.println("-------------------------------------------------------------");
            System.err.println(System.currentTimeMillis());
            System.err.println(transaction.getDate().getTime());
            System.err.println(System.currentTimeMillis()
                    - transaction.getDate().getTime());
            return transaction;
        }).filter(transaction -> transaction.getType().equals("deposit"));
        // .filter(transaction -> (System.currentTimeMillis()
        // - transaction.getDate().getTime()) > this.MONTH_DURATION_IN_MILLIS);

        System.err.println(transactions);
        System.err.println("length:" + transactions.size());
        // map(Transaction::getAmount).reduce(0.0, Double::sum);

        return 0.1;
    }
}
