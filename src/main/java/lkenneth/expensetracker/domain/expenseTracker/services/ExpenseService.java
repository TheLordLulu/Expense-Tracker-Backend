package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import lkenneth.expensetracker.domain.expenseTracker.repos.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExpenseService {
    private ExpenseRepo expenseRepo;

    @Autowired
    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public void addExpense(Expense expense){
        validateExpense(expense);
        expenseRepo.save(expense);
    }
    public List<Expense> getExpense(){
       return expenseRepo.findAllByOrderByDateDesc();
    }
    public void deleteExpense(String id){
        expenseRepo.deleteById(id);
    }



    private void validateExpense(Expense expense){
        if(expense == null){
            throw new IllegalArgumentException("Expense cannot be null");
        }
        if(expense.getTitle() == null || expense.getCategory() == null || expense.getDescription() == null || expense.getDate() == null){
            throw new IllegalArgumentException("All fields are required");
        }
        if(expense.getAmount() <= 0){
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }
}
