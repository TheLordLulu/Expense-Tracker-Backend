package lkenneth.expensetracker.domain.expenseTracker.repos;

import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepo  extends MongoRepository<Expense, String> {
    List<Expense> findExpensesByCategory(String category);
}
