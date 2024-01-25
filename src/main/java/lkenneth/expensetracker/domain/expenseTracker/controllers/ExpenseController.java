package lkenneth.expensetracker.domain.expenseTracker.controllers;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Expense;
import lkenneth.expensetracker.domain.expenseTracker.services.ExpenseService;
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

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        List<Expense> expenses = expenseService.getAll();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        try {
            expense = expenseService.create(expense);
            return new ResponseEntity<>(expense, HttpStatus.CREATED);
        } catch (ResourceCreationException e) {
            // Handle resource creation exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getById(@PathVariable String id) {
        try {
            Expense expense = expenseService.getById(id);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Expense> update(@PathVariable String id, @RequestBody Expense expenseDetail) {
        try {
            expenseDetail = expenseService.update(id, expenseDetail);
            return new ResponseEntity<>(expenseDetail, HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        expenseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Expense>> getIncomesByCategory(@RequestParam String category) {
        List<Expense> expenses = expenseService.getExpensesByCategory(category);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }
}

