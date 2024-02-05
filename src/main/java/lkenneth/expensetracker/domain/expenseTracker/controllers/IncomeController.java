package lkenneth.expensetracker.domain.expenseTracker.controllers;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.models.User;
import lkenneth.expensetracker.domain.expenseTracker.services.IncomeService;
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
@RequestMapping("/api/v1/incomes")
public class IncomeController {
    private final IncomeService incomeService;
    private final UserService userService;

    @Autowired
    public IncomeController(IncomeService incomeService, UserService userService) {
        this.incomeService = incomeService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody Income income, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Income createdIncome = incomeService.create(income, user);
        return ResponseEntity.ok(createdIncome);
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<Income> getIncomeById(@PathVariable String incomeId, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Income income = incomeService.getById(incomeId, user);
        return ResponseEntity.ok(income);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Income>> getAllIncomesByUser(@PathVariable String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Income> incomes = incomeService.getAllIncomesByUser(user);
        return ResponseEntity.ok(incomes);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Income>> getIncomesByCategory(@PathVariable String category, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        List<Income> incomes = incomeService.getIncomesByCategory(category, user);
        return ResponseEntity.ok(incomes);
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<Income> updateIncome(@PathVariable String incomeId, @RequestBody Income incomeDetail, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Income updatedIncome = incomeService.update(incomeId, incomeDetail, user);
        return ResponseEntity.ok(updatedIncome);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable String incomeId, @RequestParam String userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        incomeService.delete(incomeId, user);
        return ResponseEntity.noContent().build();
    }


}

