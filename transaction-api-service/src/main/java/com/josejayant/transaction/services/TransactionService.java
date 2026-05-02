package com.josejayant.transaction.services;

import com.josejayant.transaction.dto.TransactionDto;
import com.josejayant.transaction.dto.TransactionReq;
import com.josejayant.transaction.dto.TransactionRes;
import com.josejayant.transaction.enums.TransactionType;
import com.josejayant.transaction.models.Account;
import com.josejayant.transaction.models.Transaction;
import com.josejayant.transaction.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.josejayant.transaction.enums.TransactionStatus.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    KafkaProducerService kafkaProducerService;

    public TransactionRes saveTransaction(TransactionReq transactionReq) {

        Transaction newTransaction = new Transaction(
                transactionReq.getAccountId(),
                transactionReq.getAmount(),
                transactionReq.getType(),
                PENDING,
                null,
                LocalDateTime.now());

        Transaction savedTransaction = transactionRepository.save(newTransaction);

        TransactionRes transactionRes = new TransactionRes(
                savedTransaction.getTransactionId(),
                savedTransaction.getStatus()
        );

        kafkaProducerService.sendMessage(String.valueOf(transactionRes.getTransactionId()));

        return transactionRes;

    }


    public Optional<Transaction> getTransactionById(int transactionId) {

        return transactionRepository.findById(transactionId);
    }

    public TransactionDto getTransactionDtoById(int txnId) {

        Transaction transaction = getTransactionById(txnId).get();

        TransactionDto txnDto = new TransactionDto(
                transaction.getTransactionId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getStatus(),
                transaction.getCreatedAt()
                );

        return txnDto;
    }
}
