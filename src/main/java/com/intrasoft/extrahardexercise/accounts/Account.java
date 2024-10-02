package com.intrasoft.extrahardexercise.accounts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@Document(collection = "accounts")
@Data
@NoArgsConstructor
public class Account {

    @Id
    private int accountId;

    @NotBlank(message = "beneficiaryId is mandatory")
    private int beneficiaryId;

    public Account(int accountId, int beneficiaryId) {
        this.accountId = accountId;
        this.beneficiaryId = beneficiaryId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", beneficiaryId='" + beneficiaryId + '\'' +
                '}';
    }
}