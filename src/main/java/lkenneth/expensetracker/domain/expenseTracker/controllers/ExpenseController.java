package lkenneth.expensetracker.domain.expenseTracker.controllers;

import lkenneth.expensetracker.domain.expenseTracker.models.Expense;

import lkenneth.expensetracker.domain.expenseTracker.services.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping()
    public ResponseEntity<String> addExpense(@RequestBody Expense expense){
        expenseService.addExpense(expense);
        return ResponseEntity.ok("Expense Added");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Expense>> getExpenses(){
        List<Expense> expenses = expenseService.getExpense();
        return ResponseEntity.ok(expenses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenses(@PathVariable String id){
        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Expense Deleted");
    }
}
