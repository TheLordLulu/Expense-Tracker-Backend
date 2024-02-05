package lkenneth.expensetracker.domain.expenseTracker.controllers;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import lkenneth.expensetracker.domain.expenseTracker.models.User;
import lkenneth.expensetracker.domain.expenseTracker.services.ExpenseService;

import lkenneth.expensetracker.domain.expenseTracker.services.UserService;
import lkenneth.expensetracker.domain.expenseTracker.services.UserServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    @Autowired
    public ExpenseController(ExpenseService expenseService, UserServiceImpl userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Expense createdExpense = expenseService.create(expense, user);
        return ResponseEntity.ok(createdExpense);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String expenseId, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Expense expense = expenseService.getById(expenseId, user);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getAllExpensesByUser(@PathVariable String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Expense> expenses = expenseService.getAllExpensesByUser(user);
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String expenseId, @RequestBody Expense expenseDetail, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Expense updatedExpense = expenseService.update(expenseId, expenseDetail, user);
        return ResponseEntity.ok(updatedExpense);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Expense> expenses = expenseService.getExpensesByCategory(category, user);
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String expenseId, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        expenseService.delete(expenseId, user);
        return ResponseEntity.noContent().build();
    }
}

