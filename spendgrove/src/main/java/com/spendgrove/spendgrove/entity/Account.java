package com.spendgrove.spendgrove.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Account(String name, AccountType type) {
        this.name = name;
        this.type = type;
    }
    // Encapsulation: balance only changes through these methods,
    // never set directly from outside — protects the invariant that
    // balance always reflects the sum of applied transactions.
    public void credit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
    public void debit(BigDecimal amount) {
        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalStateException("Insufficient funds in account: " + name);
        }
        this.balance = this.balance.subtract(amount);
    }
}
