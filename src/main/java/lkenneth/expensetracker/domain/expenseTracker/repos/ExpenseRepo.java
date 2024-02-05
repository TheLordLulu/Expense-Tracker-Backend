package lkenneth.expensetracker.domain.expenseTracker.repos;

import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepo  extends MongoRepository<Expense, String> {
    List<Expense> findByUser(User user);
    List<Expense> findByUserAndCategory(User user, String category);
    Optional<Expense> findByIdAndUser(String id, User user);
}
