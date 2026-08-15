package com.spendgrove.spendgrove.repository;

import com.spendgrove.spendgrove.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// no implementation needed — Spring generates save/findById/findAll/delete at runtime
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}