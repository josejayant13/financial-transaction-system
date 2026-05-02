package com.josejayant.transaction.controllers;

import com.josejayant.transaction.dto.TransactionDto;
import com.josejayant.transaction.dto.TransactionReq;
import com.josejayant.transaction.dto.TransactionRes;
import com.josejayant.transaction.enums.TransactionType;
import com.josejayant.transaction.models.Transaction;
import com.josejayant.transaction.services.KafkaProducerService;
import com.josejayant.transaction.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    public KafkaProducerService kafkaProducerService;

    @GetMapping("/test")
    public String test(){

//        kafkaProducerService.sendMessage("Jose");;
        return "TEst endpoint";
    }

    @PostMapping("/transaction")
    public TransactionRes saveTransaction(@Valid @RequestBody TransactionReq transactionReq)
    {
        return transactionService.saveTransaction(transactionReq);
    }

    @GetMapping("/transaction/{txnId}")
    public TransactionDto getTransaction(@PathVariable int txnId)
    {
        return transactionService.getTransactionDtoById(txnId);
    }
}
