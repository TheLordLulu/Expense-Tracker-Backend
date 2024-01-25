package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

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
    public Expense create(Expense expense) throws ResourceCreationException {
        return expenseRepo.save(expense);
    }

    @Override
    public Expense getById(String id) throws ResourceNotFoundException {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
        return optionalExpense.orElseThrow(() -> new ResourceNotFoundException("No expense with id: " + id));
    }

    @Override
    public List<Expense> getAll() {
        return expenseRepo.findAll();
    }

    @Override
    public Expense update(String id, Expense expenseDetail) throws ResourceNotFoundException {
        Optional<Expense> optionalExpense = expenseRepo.findById(id);
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
        throw new ResourceNotFoundException("No expense with id: " + id);
    }

    @Override
    public void delete(String id) {
        expenseRepo.deleteById(id);
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepo.findExpensesByCategory(category);
    }
}

