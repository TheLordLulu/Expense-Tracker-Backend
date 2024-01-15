package lkenneth.expensetracker.domain.expenseTracker.controllers;


import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @PostMapping()
    public ResponseEntity<String> addIncome(@RequestBody Income income){
        incomeService.addIncome(income);
        return ResponseEntity.ok("Income Added");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Income>> getIncomes(){
        List<Income> incomes = incomeService.getIncomes();
        return ResponseEntity.ok(incomes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable String id){
        incomeService.deleteIncome(id);
        return ResponseEntity.ok("Income Deleted");
    }

}
