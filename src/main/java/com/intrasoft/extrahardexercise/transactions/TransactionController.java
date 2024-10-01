package com.intrasoft.extrahardexercise.transactions;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intrasoft.extrahardexercise.accounts.Account;
import com.intrasoft.extrahardexercise.accounts.AccountRepository;

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
    ResponseEntity<String> findBiggestDepositByBeneficiaryId(@RequestParam int beneficiaryId) throws JSONException {

        List<Transaction> transactionsByBeneficiaryId = transactionController
                .findTransactionsByBeneficiaryId(beneficiaryId);

        double max = transactionsByBeneficiaryId.stream()
                .filter(transaction -> transaction.getType().equals("withdrawal"))
                .filter(transaction -> (System.currentTimeMillis()
                        - transaction.getDate().getTime()) < this.MONTH_DURATION_IN_MILLIS)
                .map(Transaction::getAmount).reduce(0.0, Double::max);

        JSONObject response = new JSONObject();

        response.put("beneficiaryId: ", beneficiaryId).put("max", max);

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }
}
