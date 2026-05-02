package com.josejayant.transaction.dto;

import com.josejayant.transaction.enums.TransactionStatus;
import lombok.Data;

@Data
public class TransactionRes {
    private int transactionId;
    private TransactionStatus status;

    public TransactionRes(int transactionId, TransactionStatus status) {
        this.transactionId = transactionId;
        this.status = status;
    }
}
