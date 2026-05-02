package com.josejayant.transaction.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {

    @Id
    private int accountId;
    private int balance;

}
