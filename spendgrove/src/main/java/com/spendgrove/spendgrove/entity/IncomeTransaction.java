package com.spendgrove.spendgrove.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("INCOME")   // value written into the "type" column for these rows
public class IncomeTransaction extends Transaction {

    protected IncomeTransaction() {}   // Hibernate-only constructor

    public IncomeTransaction(Account account, Category category, BigDecimal amount,
                             String description, LocalDate transactionDate) {
        super(account, category, amount, description, transactionDate);
    }

    @Override
    public void apply() {
        getAccount().credit(getAmount());   // income adds money to the account
    }
}