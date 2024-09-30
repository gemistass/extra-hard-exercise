package com.intrasoft.extrahardexercise.beneficiaries;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeneficiaryRepository extends MongoRepository<Beneficiary, String> {

    List<Beneficiary> findByBeneficiaryId(String beneficiaryId);

}
