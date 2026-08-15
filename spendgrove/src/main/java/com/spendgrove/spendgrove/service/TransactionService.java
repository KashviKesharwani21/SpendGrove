package com.spendgrove.spendgrove.service;

import com.spendgrove.spendgrove.entity.*;
import com.spendgrove.spendgrove.repository.CategoryRepository;
import com.spendgrove.spendgrove.repository.TransactionRepository;
import com.spendgrove.spendgrove.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service  // marks this as a Spring-managed bean holding business logic
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    // constructor injection: Spring automatically supplies these three
    // repository beans when it creates a TransactionService
    public TransactionService(TransactionRepository transactionRepository,
                              AccountRepository accountRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional  // all DB writes here succeed together, or none do
    public ExpenseTransaction addExpense(UUID accountId, UUID categoryId, BigDecimal amount,
                                         String description, LocalDate date) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));

        ExpenseTransaction transaction =
                new ExpenseTransaction(account, category, amount, description, date);

        transaction.apply();              // polymorphic call — debits the account in memory
        accountRepository.save(account);  // persist the updated balance
        return transactionRepository.save(transaction); // persist the transaction row
    }
}