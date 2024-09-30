package com.intrasoft.benef1.beneficiaries;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BeneficiaryReposiroty extends MongoRepository<Beneficiary, String> {

    List<Beneficiary> findByBeneficiaryId(String beneficiaryId);

}
