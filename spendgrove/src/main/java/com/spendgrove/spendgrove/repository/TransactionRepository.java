package com.spendgrove.spendgrove.repository;

import com.spendgrove.spendgrove.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

// note: typed against the abstract base class — Hibernate handles fetching
// the correct concrete subtype (Income/Expense/Transfer) automatically
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    // Spring Data JPA reads this method name and generates the SQL itself
    List<Transaction> findByAccountId(UUID accountId);
}