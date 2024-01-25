package lkenneth.expensetracker.domain.expenseTracker.services;

import lkenneth.expensetracker.domain.core.exceptions.ResourceCreationException;
import lkenneth.expensetracker.domain.core.exceptions.ResourceNotFoundException;
import lkenneth.expensetracker.domain.expenseTracker.models.Income;

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
    public Income create(Income income) throws ResourceCreationException {
        return incomeRepo.save(income);
    }

    @Override
    public Income getById(String id) throws ResourceNotFoundException {
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        return optionalIncome.orElseThrow(() -> new ResourceNotFoundException("No income with id: " + id));
    }

    @Override
    public List<Income> getAll() {
        return incomeRepo.findAll();
    }

    @Override
    public Income update(String id, Income incomeDetail) throws ResourceNotFoundException {
        Optional<Income> optionalIncome = incomeRepo.findById(id);
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
        throw new ResourceNotFoundException("No income with id: " + id);
    }

    @Override
    public void delete(String id) {
        incomeRepo.deleteById(id);
    }

    @Override
    public List<Income> getIncomesByCategory(String category) {
        return incomeRepo.findIncomesByCategory(category);
    }
}


