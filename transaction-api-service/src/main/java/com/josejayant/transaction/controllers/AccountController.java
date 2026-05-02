package com.josejayant.transaction.controllers;

import com.josejayant.transaction.models.Account;
import com.josejayant.transaction.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/allaccounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }



}
