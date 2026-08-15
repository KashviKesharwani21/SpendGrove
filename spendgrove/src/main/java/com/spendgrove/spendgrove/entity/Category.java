package com.spendgrove.spendgrove.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity                          // maps this class to a DB table
@Table(name = "categories")
@Getter                          // Lombok: auto-generates getId(), getName(), getType()
@NoArgsConstructor               // required by Hibernate to build objects when reading from DB
public class Category {

    @Id                                              // primary key
    @GeneratedValue(strategy = GenerationType.UUID)  // Hibernate auto-generates the UUID
    private UUID id;

    @Column(nullable = false)    // NOT NULL constraint in Postgres
    private String name;

    @Enumerated(EnumType.STRING) // store enum as text ("INCOME"), not a number
    @Column(nullable = false)
    private CategoryType type;

    // the only constructor app code should use — forces name+type at creation
    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }
}