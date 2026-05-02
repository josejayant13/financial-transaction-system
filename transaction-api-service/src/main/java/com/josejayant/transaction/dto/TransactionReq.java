package com.josejayant.transaction.dto;

import com.josejayant.transaction.enums.TransactionType;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class TransactionReq
{

    @NotNull(message = "AccountId is required")
    private int accountId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private int amount;

    @NotNull(message = "Transaction type is required")
    private TransactionType type;
}
