package com.josejayant.transaction.dto;

import com.josejayant.transaction.enums.TransactionStatus;
import com.josejayant.transaction.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDto {


    private int transactionId;
    private int amount;
    private TransactionType type; //(DEBIT/CREDIT)
    private TransactionStatus status; //(PENDING, PROCESSING, SUCCESS, FAILED))
    private LocalDateTime createdAt;

    public TransactionDto(int amount, int transactionId, TransactionType type,
                          TransactionStatus status, LocalDateTime createdAt)
    {
        this.amount = amount;
        this.transactionId = transactionId;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }
}
