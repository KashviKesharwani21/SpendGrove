package com.spendgrove.spendgrove.repository;

import com.spendgrove.spendgrove.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}