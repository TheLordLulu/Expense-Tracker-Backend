package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.models.User;

import java.util.List;

public interface IncomeService {
    Income create(Income income, User user) throws ResourceCreationException;
    Income getById(String incomeId, User user) throws ResourceNotFoundException;
    List<Income> getAll();
    Income update(String incomeId, Income incomeDetail, User user) throws ResourceNotFoundException;
    void delete(String incomeId, User user);
    List<Income> getIncomesByCategory( User user, String category);

    List<Income> getIncomesByCategory(String category, User user);

    List<Income> getAllIncomesByUser(User user);
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
