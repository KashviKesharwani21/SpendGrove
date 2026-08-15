package com.spendgrove.spendgrove.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // one table for Income/Expense/Transfer
@DiscriminatorColumn(name = "type")                     // hidden column marking which subclass a row is
@Getter
@NoArgsConstructor
public abstract class Transaction {          // abstract: you never create a plain Transaction directly

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)             // many transactions -> one account (FK relationship)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne                                // nullable: transfers don't use a category
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false)
    private BigDecimal amount;

    private String description;

    @Column(nullable = false)
    private LocalDate transactionDate;

    // shared constructor logic — subclasses call this via super(...)
    protected Transaction(Account account, Category category, BigDecimal amount,
                          String description, LocalDate transactionDate) {
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    // polymorphism: each subclass decides HOW it affects account balances
    public abstract void apply();
}