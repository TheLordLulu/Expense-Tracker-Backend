package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.models.User;
import lkenneth.expensetracker.domain.expenseTracker.repos.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepo expenseRepo;

    @Autowired
    public ExpenseServiceImpl(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public Expense create(Expense expense, User user) throws ResourceCreationException {
        expense.setUser(user);
        return expenseRepo.save(expense);
    }

    @Override
    public Expense getById(String expenseId, User user) throws ResourceNotFoundException {
        Optional<Expense> optionalExpense = expenseRepo.findByIdAndUser(expenseId, user);
        return optionalExpense.orElse(null);
    }

    @Override
    public List<Expense> getAll() {
        return expenseRepo.findAll();
    }

    @Override
    public Expense update(String expenseId, Expense expenseDetail, User user) throws ResourceNotFoundException {
        Optional<Expense> optionalExpense = expenseRepo.findByIdAndUser(expenseId, user);
        if (optionalExpense.isPresent()) {
            Expense existingExpense = optionalExpense.get();
            // Update existingExpense properties with expenseDetail properties
            existingExpense.setTitle(expenseDetail.getTitle());
            existingExpense.setAmount(expenseDetail.getAmount());
            existingExpense.setType(expenseDetail.getType());
            existingExpense.setDate(expenseDetail.getDate());
            existingExpense.setCategory(expenseDetail.getCategory());
            existingExpense.setDescription(expenseDetail.getDescription());

            return expenseRepo.save(existingExpense);
        }
        return null; // Handle not found or unauthorized access
    }

    @Override
    public void delete(String expenseId, User user) {
        expenseRepo.deleteById(expenseId);
    }

    @Override
    public List<Expense> getExpensesByCategory(String category, User user) {
        return expenseRepo.findByUserAndCategory(user, category);
    }

    @Override
    public List<Expense> getAllExpensesByUser(User user) {
        return expenseRepo.findByUser(user);
    }
}

