package com.josejayant.transaction.services;

import com.josejayant.transaction.exception.InsufficientBalanceException;
import com.josejayant.transaction.models.Account;
import com.josejayant.transaction.models.Transaction;
import com.josejayant.transaction.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.josejayant.transaction.enums.TransactionStatus.*;
import static com.josejayant.transaction.enums.TransactionStatus.FAILED;

@Service
public class KafkaConsumerService
{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @KafkaListener(topics = "transaction-topic", groupId = "my-group")
    public void consume(String message){
        System.out.println("Received TXN ID: " + message);

        int transactionId = Integer.parseInt(message);

        Transaction transaction = transactionRepository.findById(transactionId).get();

        processTransaction(transaction);
    }

    private void processTransaction(Transaction transaction)
    {

        Account account = accountService.getAccount(transaction.getAccountId());

        int transactionAmount = transaction.getAmount();
        int accountBalance = account.getBalance();

        transaction.setStatus(PROCESSING);
        switch (transaction.getType())
        {
            case DEBIT:
                if (transactionAmount > accountBalance)
                {
                    transaction.setStatus(FAILED);
                    transaction.setFailureReason("Debit amount is greater than balance");
                    transactionRepository.save(transaction);
                    throw new InsufficientBalanceException("Insufficient balance");
                }
                else {
                    account.setBalance(accountBalance - transactionAmount);
                }
            break;
            case CREDIT:
                account.setBalance(accountBalance + transactionAmount);
            break;
            default:
                transaction.setFailureReason("Invalid Transaction Type");
                transaction.setStatus(FAILED);
        }

        accountService.updateAccountBalance(account);
        transaction.setStatus(SUCCESS);
        transactionRepository.save(transaction);
    }

}
