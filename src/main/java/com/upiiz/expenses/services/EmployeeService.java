package com.upiiz.expenses.services;

import com.upiiz.expenses.entities.Employees;
import com.upiiz.expenses.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    ExpenseRepository expenseRepository;

    public List<Employees> getAllEmployees() {
        return expenseRepository.findAll();
    }

    public Optional<Employees> getEmployeeById(Long id) {
        return expenseRepository.findById(id);
    }

    public Employees createEmployee(Employees employees) {
        return expenseRepository.save(employees);
    }

    public Employees updateEmployee(Employees employees) {
        return expenseRepository.save(employees);
    }

    public void deleteEmployee(Long id) {
        expenseRepository.deleteById(id);
    }
}