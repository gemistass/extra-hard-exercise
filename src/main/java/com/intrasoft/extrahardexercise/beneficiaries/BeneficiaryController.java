package com.intrasoft.extrahardexercise.beneficiaries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/beneficiaries")
@Slf4j
public class BeneficiaryController {

    @Autowired
    BeneficiaryRepository beneficiaryRepository;

    @GetMapping("/{id}")
    Beneficiary one(@PathVariable int id) {

        return beneficiaryRepository.findById(id).get();

    }

}
