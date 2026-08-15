package com.spendgrove.spendgrove.controller;

import com.spendgrove.spendgrove.entity.ExpenseTransaction;
import com.spendgrove.spendgrove.entity.IncomeTransaction;
import com.spendgrove.spendgrove.entity.TransferTransaction;
import com.spendgrove.spendgrove.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController                    // Bean + "returns data directly, not a view"
@RequestMapping("/api/transactions") // shared URL prefix for every method below
public class TransactionController {

    private final TransactionService transactionService;

    // constructor injection — same pattern as TransactionService itself
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/expense")   // handles POST /api/transactions/expense
    public ResponseEntity<ExpenseTransaction> addExpense(@RequestBody AddExpenseRequest request) {
        ExpenseTransaction transaction = transactionService.addExpense(
                request.accountId(),
                request.categoryId(),
                request.amount(),
                request.description(),
                request.transactionDate()
        );
        return ResponseEntity.ok(transaction);
    }

    // a small nested record representing the expected JSON request body shape
    public record AddExpenseRequest(
            UUID accountId,
            UUID categoryId,
            BigDecimal amount,
            String description,
            LocalDate transactionDate
    ){}

    @PostMapping("/income")
    public ResponseEntity<IncomeTransaction> addIncome(@RequestBody AddIncomeRequest request) {
        IncomeTransaction transaction = transactionService.addIncome(
                request.accountId(), request.categoryId(), request.amount(),
                request.description(), request.transactionDate()
        );
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferTransaction> addTransfer(@RequestBody AddTransferRequest request) {
        TransferTransaction transaction = transactionService.addTransfer(
                request.fromAccountId(), request.toAccountId(), request.amount(),
                request.description(), request.transactionDate()
        );
        return ResponseEntity.ok(transaction);
    }

    public record AddIncomeRequest(
            UUID accountId, UUID categoryId, BigDecimal amount,
            String description, LocalDate transactionDate
    ) {}

    public record AddTransferRequest(
            UUID fromAccountId, UUID toAccountId, BigDecimal amount,
            String description, LocalDate transactionDate
    ) {}
}