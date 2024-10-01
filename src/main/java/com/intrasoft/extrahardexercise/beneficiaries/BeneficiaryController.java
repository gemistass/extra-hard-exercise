package com.intrasoft.extrahardexercise.beneficiaries;

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

import com.intrasoft.extrahardexercise.transactions.Transaction;
import com.intrasoft.extrahardexercise.transactions.TransactionController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/beneficiaries")
@Slf4j
public class BeneficiaryController {

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @Autowired
    TransactionController transactionController;

    @GetMapping("/{id}")
    Beneficiary one(@PathVariable int id) {

        return beneficiaryRepository.findById(id).get();

    }

    Beneficiary findByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {

        return beneficiaryRepository.findByFirstNameAndLastName(firstName, lastName);

    }

    @GetMapping("/sum")
    ResponseEntity<String> calculateBeneficiarySum(@RequestParam String firstName, @RequestParam String lastName)
            throws JSONException {

        JSONObject response = new JSONObject();
        int beneficiaryId = findByFirstNameAndLastName(firstName, lastName).getBeneficiaryId();

        List<Transaction> transactions = transactionController
                .findTransactionsByBeneficiaryId(beneficiaryId);

        float beneficiarySum = transactions.stream().map(Transaction::getAmount).reduce(0.0f, Float::sum);

        response.put("beneficiary", firstName.concat(" " + lastName)).put("sum", beneficiarySum);

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);

    }

}
