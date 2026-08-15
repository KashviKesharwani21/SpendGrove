package com.spendgrove.spendgrove.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("TRANSFER")
@Getter
public class TransferTransaction extends Transaction {

    @ManyToOne(optional = false)          // the destination account (source = inherited "account" field)
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    protected TransferTransaction() {}

    public TransferTransaction(Account fromAccount, Account toAccount, BigDecimal amount,
                               String description, LocalDate transactionDate) {
        // reuse inherited "account" field as the source account; category is null for transfers
        super(fromAccount, null, amount, description, transactionDate);
        this.toAccount = toAccount;
    }

    @Override
    public void apply() {
        getAccount().debit(getAmount());   // pull from source
        toAccount.credit(getAmount());     // push to destination
    }
}