package com.intrasoft.extrahardexercise.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/accounts")
@Slf4j
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/{id}")
    Account one(@PathVariable int id) {

        return accountRepository.findById(id).get();
    }

    @GetMapping("")
    public List<Account> getAccountsByBeneficiaryId(@RequestParam int beneficiaryId) {

        return accountRepository.findByBeneficiaryId(beneficiaryId);
    }

}
