package lkenneth.expensetracker.domain.expenseTracker.controllers;


import com.google.firebase.auth.FirebaseAuthException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.firebase.FirebaseAuthService;
import lkenneth.expensetracker.domain.expenseTracker.models.SignUpRequest;
import lkenneth.expensetracker.domain.expenseTracker.models.User;
import lkenneth.expensetracker.domain.expenseTracker.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private final UserService userService;
    private final FirebaseAuthService firebaseAuthService;

    @Autowired
    public UserController(UserService userService, FirebaseAuthService firebaseAuthService) {
        this.userService = userService;
        this.firebaseAuthService = firebaseAuthService;
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> request) {
        try {
            String firebaseToken = request.get("firebaseToken");
            String name = request.get("name");
            String email = request.get("email");
            String password = request.get("password");

            String firebaseUid = firebaseAuthService.verifyFirebaseTokenAndGetUserId(firebaseToken);

            User user = new User();
            user.setId(firebaseUid); // Use Firebase UID as the ID
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            User createdUser = userService.createUser(email, password, name);

            return ResponseEntity.ok(createdUser);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        boolean deleted = userService.deleteUserById(userId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
    }



}
