package lkenneth.expensetracker.domain.expenseTracker.controllers;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.services.IncomeService;
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

    @Autowired
    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public ResponseEntity<List<Income>> getAll() {
        List<Income> incomes = incomeService.getAll();
        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Income> create(@RequestBody Income income) {
        try {
            income = incomeService.create(income);
            return new ResponseEntity<>(income, HttpStatus.CREATED);
        } catch (ResourceCreationException e) {
            // Handle resource creation exception
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getById(@PathVariable String id) {
        try {
            Income income = incomeService.getById(id);
            return new ResponseEntity<>(income, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Income> update(@PathVariable String id, @RequestBody Income incomeDetail) {
        try {
            incomeDetail = incomeService.update(id, incomeDetail);
            return new ResponseEntity<>(incomeDetail, HttpStatus.ACCEPTED);
        } catch (ResourceNotFoundException e) {
            // Handle resource not found exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        incomeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Income>> getIncomesByCategory(@RequestParam String category) {
        List<Income> incomes = incomeService.getIncomesByCategory(category);
        return new ResponseEntity<>(incomes, HttpStatus.OK);
    }
}

