package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.expenseTracker.models.User;
import lkenneth.expensetracker.domain.expenseTracker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User createUser(String email, String password, String name) {
        // Check if user already exists with the given email
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with the provided email already exists.");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);

        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public boolean deleteUserById(String userId) {
        // Implementation for deleting a user
        // ...

        // Example: Deleting a user from MongoDB
        if (userRepo.existsById(userId)) {
            userRepo.deleteById(userId);
            return true;
        }
        return false;
    }
}
