package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;

import lkenneth.expensetracker.domain.expenseTracker.models.User;
import lkenneth.expensetracker.domain.expenseTracker.repos.IncomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeRepo incomeRepo;

    @Autowired
    public IncomeServiceImpl(IncomeRepo incomeRepo) {
        this.incomeRepo = incomeRepo;
    }

    @Override
    public Income create(Income income, User user) throws ResourceCreationException {
        income.setUser(user);
        return incomeRepo.save(income);
    }


    @Override
    public Income getById(String incomeId, User user) throws ResourceNotFoundException  {
        Optional<Income> optionalIncome = incomeRepo.findByIdAndUser(incomeId, user);
        return optionalIncome.orElse(null);
    }

    @Override
    public List<Income> getAll() {
        return incomeRepo.findAll();
    }

    @Override
    public Income update(String incomeId, Income incomeDetail, User user )  throws ResourceNotFoundException {
        Optional<Income> optionalIncome = incomeRepo.findByIdAndUser(incomeId, user);
        if (optionalIncome.isPresent()) {
            Income existingIncome = optionalIncome.get();
            // Update existingIncome properties with incomeDetail properties
            existingIncome.setTitle(incomeDetail.getTitle());
            existingIncome.setAmount(incomeDetail.getAmount());
            existingIncome.setType(incomeDetail.getType());
            existingIncome.setDate(incomeDetail.getDate());
            existingIncome.setCategory(incomeDetail.getCategory());
            existingIncome.setDescription(incomeDetail.getDescription());

            return incomeRepo.save(existingIncome);
        }
        return null; // Handle not found or unauthorized access
    }

    @Override
    public void delete(String incomeId, User user) {
        incomeRepo.deleteById(incomeId);
    }

    @Override
    public List<Income> getIncomesByCategory(User user, String category) {
        return incomeRepo.findByUserAndCategory(user, category);
    }

    @Override
    public List<Income> getIncomesByCategory(String category, User user) {
        return incomeRepo.findByUserAndCategory(user, category);
    }

    @Override
    public List<Income> getAllIncomesByUser(User user) {
        return incomeRepo.findByUser(user);
    }
}


