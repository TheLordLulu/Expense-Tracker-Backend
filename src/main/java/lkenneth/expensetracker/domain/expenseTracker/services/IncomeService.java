package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;
import lkenneth.expensetracker.domain.expenseTracker.repos.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    private IncomeRepo incomeRepo;

    @Autowired
    public IncomeService(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    public void addIncome(Income income){
        validateIncome(income);
        incomeRepo.save(income);
    }
    public List<Income> getIncomes(){
        return incomeRepo.findAllByOrderByDateDesc();
    }
    public void deleteIncome(String id){
        incomeRepo.deleteById(id);
    }



    private void validateIncome(Income income){
        if(income == null){
            throw new IllegalArgumentException("Income cannot be null");
        }
        if(income.getTitle() == null || income.getCategory() == null || income.getDescription() == null || income.getDate() == null){
            throw new IllegalArgumentException("All fields are required");
        }
        if(income.getAmount() <= 0){
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }
}
