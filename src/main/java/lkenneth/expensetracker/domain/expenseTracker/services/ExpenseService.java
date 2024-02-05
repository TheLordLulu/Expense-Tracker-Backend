package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Expense;
import lkenneth.expensetracker.domain.expenseTracker.models.User;

import java.util.List;

public interface ExpenseService {
    Expense create(Expense expense, User user) throws ResourceCreationException;
    Expense getById(String expenseId, User user) throws ResourceNotFoundException;
    List<Expense> getAll();
    Expense update(String expenseId, Expense expenseDetail, User user) throws ResourceNotFoundException;
    void delete(String expenseId, User user);
    List<Expense> getExpensesByCategory(String category, User user);
    List<Expense> getAllExpensesByUser(User user);
}

