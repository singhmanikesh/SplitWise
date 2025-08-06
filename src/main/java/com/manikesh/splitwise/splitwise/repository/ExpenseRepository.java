package com.manikesh.splitwise.splitwise.repository;

import com.manikesh.splitwise.splitwise.dto.ExpenseResponse;
import com.manikesh.splitwise.splitwise.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByGroupId(Long groupId);
}
