package com.intrasoft.extrahardexercise.beneficiaries;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@Document(collection = "beneficiaries")
@Data
@NoArgsConstructor
public class Beneficiary {

    @Id
    private int beneficiaryId;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    public Beneficiary(int beneficiaryId, String firstName, String lastName) {
        this.beneficiaryId = beneficiaryId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "beneficiaryId='" + beneficiaryId + '\'' +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                '}';
    }
}