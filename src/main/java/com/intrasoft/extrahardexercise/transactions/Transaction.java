package com.intrasoft.extrahardexercise.transactions;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Validated
@Document(collection = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    private int transactionId;

    @NotBlank(message = "accountId is mandatory")
    private int accountId;

    @NotBlank(message = "amount is mandatory")
    private float amount;

    @NotBlank(message = "type is mandatory")
    @Pattern(regexp = "(withdrawal|deposit)$", message = "Invalid type. Accepted values:[withdrawal,deposit]")
    private String type;

    @NotBlank(message = "date is mandatory")
    @JsonFormat(pattern="MM-dd-yy")
    private Date date;
     
    

    public Transaction(int transactionId, int accountId, float amount, String type, Date date) {
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
                ", date=" + date +
                '}';
    }
}