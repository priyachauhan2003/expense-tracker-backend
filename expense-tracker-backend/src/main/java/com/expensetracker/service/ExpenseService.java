package com.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expensetracker.model.Expense;
import com.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // CREATE
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // READ - all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // READ - one expense
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);

        if (expense.isEmpty()) {
            throw new RuntimeException("Expense not found with id: " + id);
        }

        return expense.get();
    }

    // UPDATE
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = getExpenseById(id);

        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setExpenseDate(updatedExpense.getExpenseDate());
        existingExpense.setDescription(updatedExpense.getDescription());

        return expenseRepository.save(existingExpense);
    }

    // DELETE
    public void deleteExpense(Long id) {
        Expense existingExpense = getExpenseById(id);

        expenseRepository.delete(existingExpense);
    }
}