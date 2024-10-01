package com.intrasoft.extrahardexercise.beneficiaries;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeneficiaryRepository extends MongoRepository<Beneficiary, Integer> {

    List<Beneficiary> findByBeneficiaryId(int beneficiaryId);

    Beneficiary findByFirstName(String firstName);

    Beneficiary findByFirstNameAndLastName(String firstName, String lastName);

}
