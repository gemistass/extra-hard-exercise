package com.intrasoft.extrahardexercise.transactions;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Validated
@Document(collection = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    private String transactionId;

    @NotBlank(message = "accountId is mandatory")
    private String accountId;

    @NotBlank(message = "amount is mandatory")
    private String amount;

    @NotBlank(message = "type is mandatory")

    private String type;

    @NotBlank(message = "date is mandatory")
    private String date;

    public Transaction(String transactionId, String accountId, String amount, String type, String date) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountId=" + accountId +
                ", amount=" + amount +
                ", type=" + type +
                '}';
    }
}