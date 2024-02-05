package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.expenseTracker.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {
    User createUser(String email, String password, String name);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(String userId);
    boolean deleteUserById(String userId);
}
