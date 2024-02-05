package lkenneth.expensetracker.domain.expenseTracker.repos;

import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IncomeRepo extends MongoRepository<Income, String> {
    List<Income> findByUser(User user);
    List<Income> findByUserAndCategory(User user, String category);
    Optional<Income> findByIdAndUser(String id, User user);
}
