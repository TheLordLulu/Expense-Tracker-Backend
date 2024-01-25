package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense create(Expense expense) throws ResourceCreationException;
    Expense getById(String id) throws ResourceNotFoundException;
    List<Expense> getAll();
    Expense update(String id, Expense expenseDetail) throws ResourceNotFoundException;
    void delete(String id);
    List<Expense> getExpensesByCategory(String category);
}

