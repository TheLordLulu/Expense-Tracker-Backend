package lkenneth.expensetracker.domain.expenseTracker.repos;

import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IncomeRepo extends MongoRepository<Income, String> {
    List<Income> findIncomesByCategory(String category);
}
