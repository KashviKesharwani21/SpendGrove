package com.spendgrove.spendgrove.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("EXPENSE")
public class ExpenseTransaction extends Transaction {

    protected ExpenseTransaction() {}

    public ExpenseTransaction(Account account, Category category, BigDecimal amount,
                              String description, LocalDate transactionDate) {
        super(account, category, amount, description, transactionDate);
    }

    @Override
    public void apply() {
        getAccount().debit(getAmount());   // expense removes money from the account
    }
}