package com.josejayant.transaction.models;

import com.josejayant.transaction.enums.TransactionStatus;
import com.josejayant.transaction.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private int accountId;
    private int amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type; //(DEBIT/CREDIT)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status; //(PENDING, PROCESSING, SUCCESS, FAILED)
    private String failureReason; //(nullable)
    private LocalDateTime createdAt;

    public Transaction(int accountId, int amount, TransactionType type, TransactionStatus status,
                       String failureReason, LocalDateTime createdAt) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.failureReason = failureReason;
        this.createdAt = createdAt;
    }

    public Transaction(){

    }
}
