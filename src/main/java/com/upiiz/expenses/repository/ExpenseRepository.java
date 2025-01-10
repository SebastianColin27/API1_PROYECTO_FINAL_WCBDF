package com.upiiz.expenses.repository;

import com.upiiz.expenses.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Employees, Long> {
}