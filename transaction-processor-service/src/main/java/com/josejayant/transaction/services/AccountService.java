package com.josejayant.transaction.services;

import com.josejayant.transaction.exception.AccountNotFoundException;
import com.josejayant.transaction.models.Account;
import com.josejayant.transaction.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAllAccounts() {

        return accountRepository.findAll();
    }

    public Account getAccount(int accountId) {
        return accountRepository
                .findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + accountId));
    }

    public void updateAccountBalance(Account account) {
        accountRepository.save(account);
    }
}
