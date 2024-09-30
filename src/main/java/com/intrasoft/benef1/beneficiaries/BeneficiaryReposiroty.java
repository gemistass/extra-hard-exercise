package com.intrasoft.benef1.beneficiaries;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeneficiaryReposiroty extends MongoRepository<Beneficiary, String> {

    List<Beneficiary> findByBeneficiaryId(String beneficiaryId);

}
