package lkenneth.expensetracker.domain.expenseTracker.repos;
import org.springframework.data.mongodb.repository.MongoRepository;
import lkenneth.expensetracker.domain.expenseTracker.models.User;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
}
