package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;

import java.util.List;

public interface IncomeService {
    Income create(Income income) throws ResourceCreationException;
    Income getById(String id) throws ResourceNotFoundException;
    List<Income> getAll();
    Income update(String id, Income incomeDetail) throws ResourceNotFoundException;
    void delete(String id);
    List<Income> getIncomesByCategory(String category);
}







//   private void validateIncome(Income income){
//        if(income == null){
//            throw new IllegalArgumentException("Income cannot be null");
//        }
//        if(income.getTitle() == null || income.getCategory() == null || income.getDescription() == null || income.getDate() == null){
//            throw new IllegalArgumentException("All fields are required");
//        }
//        if(income.getAmount() <= 0){
//            throw new IllegalArgumentException("Amount must be a positive number");
//        }
//    }
